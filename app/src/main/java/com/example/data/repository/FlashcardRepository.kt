package com.example.data.repository

import com.example.data.local.FlashcardDao
import com.example.data.local.UserStatsDao
import com.example.data.local.InitialData
import com.example.data.model.FlashcardEntity
import com.example.data.model.UserStatsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.max

enum class SrsRating {
    AGAIN, // Rating 1
    HARD,  // Rating 2
    GOOD,  // Rating 3
    EASY   // Rating 4
}

class FlashcardRepository(
    private val flashcardDao: FlashcardDao,
    private val userStatsDao: UserStatsDao
) {
    val allFlashcards: Flow<List<FlashcardEntity>> = flashcardDao.getAllFlashcards()
    val favoriteFlashcards: Flow<List<FlashcardEntity>> = flashcardDao.getFavoriteFlashcards()
    val userStats: Flow<UserStatsEntity?> = userStatsDao.getUserStatsFlow()

    suspend fun checkAndSeedDatabase() {
        val count = flashcardDao.getFlashcardCount()
        if (count == 0) {
            flashcardDao.insertAll(InitialData.getInitialCards())
        }
        val stats = userStatsDao.getUserStats()
        if (stats == null) {
            val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            userStatsDao.insertOrUpdate(
                UserStatsEntity(
                    id = 1,
                    dailyGoal = 10,
                    cardsReviewedToday = 0,
                    lastActiveDate = todayStr,
                    streakDays = 1
                )
            )
        } else {
            // Check streak & daily reset
            val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            if (stats.lastActiveDate != todayStr) {
                userStatsDao.insertOrUpdate(
                    stats.copy(
                        cardsReviewedToday = 0,
                        lastActiveDate = todayStr,
                        streakDays = stats.streakDays + 1
                    )
                )
            }
        }
    }

    fun getFilteredCards(niveau: String?, theme: String?): Flow<List<FlashcardEntity>> {
        val levelFilter = if (niveau.isNull_or_Empty() || niveau == "Tous") null else niveau
        val themeFilter = if (theme.isNull_or_Empty() || theme == "Tous") null else theme
        return flashcardDao.getFilteredFlashcards(levelFilter, themeFilter)
    }

    private fun String?.isNull_or_Empty(): Boolean = this.isNullOrEmpty()

    fun searchCards(query: String): Flow<List<FlashcardEntity>> {
        return flashcardDao.searchFlashcards(query)
    }

    fun getDueCards(): Flow<List<FlashcardEntity>> {
        return flashcardDao.getDueFlashcards(System.currentTimeMillis())
    }

    suspend fun toggleFavorite(card: FlashcardEntity) {
        flashcardDao.update(card.copy(isFavorite = !card.isFavorite))
    }

    suspend fun processSrsReview(card: FlashcardEntity, rating: SrsRating) {
        var repetitions = card.srsRepetitions
        var interval = card.srsInterval
        var easeFactor = card.srsEaseFactor

        when (rating) {
            SrsRating.AGAIN -> {
                repetitions = 0
                interval = 1
                easeFactor = max(1.3f, easeFactor - 0.2f)
            }
            SrsRating.HARD -> {
                interval = max(1, (interval * 1.2f).toInt())
                easeFactor = max(1.3f, easeFactor - 0.15f)
            }
            SrsRating.GOOD -> {
                repetitions += 1
                interval = when (repetitions) {
                    1 -> 1
                    2 -> 3
                    else -> max(1, (interval * easeFactor).toInt())
                }
            }
            SrsRating.EASY -> {
                repetitions += 1
                interval = when (repetitions) {
                    1 -> 2
                    2 -> 6
                    else -> max(1, (interval * easeFactor * 1.3f).toInt())
                }
                easeFactor += 0.15f
            }
        }

        val nextReview = System.currentTimeMillis() + (interval * 86400000L)
        val isMastered = repetitions >= 3 || interval >= 14

        val updatedCard = card.copy(
            srsRepetitions = repetitions,
            srsInterval = interval,
            srsEaseFactor = easeFactor,
            nextReviewDate = nextReview,
            mastered = isMastered,
            lastReviewedDate = System.currentTimeMillis()
        )

        flashcardDao.update(updatedCard)
        incrementCardsReviewedToday()
    }

    private suspend fun incrementCardsReviewedToday() {
        val current = userStatsDao.getUserStats() ?: UserStatsEntity(id = 1)
        val updated = current.copy(
            cardsReviewedToday = current.cardsReviewedToday + 1,
            totalReviews = current.totalReviews + 1
        )
        userStatsDao.insertOrUpdate(updated)
    }

    suspend fun updateSpeakingScore(scorePercent: Int) {
        val current = userStatsDao.getUserStats() ?: UserStatsEntity(id = 1)
        userStatsDao.insertOrUpdate(
            current.copy(
                totalSpeakingAttempts = current.totalSpeakingAttempts + 1,
                totalSpeakingScoreSum = current.totalSpeakingScoreSum + scorePercent
            )
        )
    }

    suspend fun updateDailyGoal(newGoal: Int) {
        val current = userStatsDao.getUserStats() ?: UserStatsEntity(id = 1)
        userStatsDao.insertOrUpdate(current.copy(dailyGoal = newGoal))
    }

    suspend fun insertCustomFlashcard(card: FlashcardEntity) {
        flashcardDao.insert(card)
    }
}
