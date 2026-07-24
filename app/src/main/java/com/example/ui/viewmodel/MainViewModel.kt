package com.example.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.model.FlashcardEntity
import com.example.data.model.UserStatsEntity
import com.example.data.repository.FlashcardRepository
import com.example.data.repository.SrsRating
import com.example.data.speech.SpeakingEvaluation
import com.example.data.speech.SpeechEvaluationUtils
import com.example.data.speech.TextToSpeechHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FlashcardRepository
    val ttsHelper: TextToSpeechHelper = TextToSpeechHelper(application)

    // Filter states
    val selectedNiveau = MutableStateFlow("Tous") // "Tous", "A2", "B1", "B2"
    val selectedTheme = MutableStateFlow("Tous")
    val searchQuery = MutableStateFlow("")

    // Flashcards list
    val allCards: StateFlow<List<FlashcardEntity>>
    val filteredCards: StateFlow<List<FlashcardEntity>>
    val favoriteCards: StateFlow<List<FlashcardEntity>>
    val dueCards: StateFlow<List<FlashcardEntity>>
    val userStats: StateFlow<UserStatsEntity?>

    // Active deck state
    val currentDeckIndex = MutableStateFlow(0)
    val isCardFlipped = MutableStateFlow(false)

    // Speaking Lab state
    val selectedSpeakingCard = MutableStateFlow<FlashcardEntity?>(null)
    val userSpokenResult = MutableStateFlow<SpeakingEvaluation?>(null)
    val isRecording = MutableStateFlow(false)

    init {
        val database = AppDatabase.getDatabase(application, viewModelScope)
        repository = FlashcardRepository(database.flashcardDao(), database.userStatsDao())

        viewModelScope.launch {
            repository.checkAndSeedDatabase()
        }

        allCards = repository.allFlashcards
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        favoriteCards = repository.favoriteFlashcards
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        dueCards = repository.getDueCards()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        userStats = repository.userStats
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

        filteredCards = combine(
            allCards,
            selectedNiveau,
            selectedTheme,
            searchQuery
        ) { cards, niveau, theme, query ->
            cards.filter { card ->
                val matchesNiveau = (niveau == "Tous" || card.niveau.equals(niveau, ignoreCase = true))
                val matchesTheme = (theme == "Tous" || card.theme.equals(theme, ignoreCase = true))
                val matchesQuery = query.isEmpty() ||
                        card.phraseMalagasy.contains(query, ignoreCase = true) ||
                        card.phraseAnglais.contains(query, ignoreCase = true) ||
                        card.vocabulaire.contains(query, ignoreCase = true)
                matchesNiveau && matchesTheme && matchesQuery
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    }

    fun setNiveauFilter(niveau: String) {
        selectedNiveau.value = niveau
        currentDeckIndex.value = 0
        isCardFlipped.value = false
    }

    fun setThemeFilter(theme: String) {
        selectedTheme.value = theme
        currentDeckIndex.value = 0
        isCardFlipped.value = false
    }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
        currentDeckIndex.value = 0
    }

    fun flipCard() {
        isCardFlipped.value = !isCardFlipped.value
    }

    fun nextCard(deckSize: Int) {
        if (deckSize > 0) {
            currentDeckIndex.value = (currentDeckIndex.value + 1) % deckSize
            isCardFlipped.value = false
        }
    }

    fun previousCard(deckSize: Int) {
        if (deckSize > 0) {
            currentDeckIndex.value = if (currentDeckIndex.value > 0) currentDeckIndex.value - 1 else deckSize - 1
            isCardFlipped.value = false
        }
    }

    fun toggleFavorite(card: FlashcardEntity) {
        viewModelScope.launch {
            repository.toggleFavorite(card)
        }
    }

    fun submitSrsRating(card: FlashcardEntity, rating: SrsRating, deckSize: Int) {
        viewModelScope.launch {
            repository.processSrsReview(card, rating)
            nextCard(deckSize)
        }
    }

    fun speakText(text: String) {
        ttsHelper.speak(text)
    }

    fun evaluateSpokenSpeech(spokenText: String, targetCard: FlashcardEntity) {
        val evaluation = SpeechEvaluationUtils.evaluateSpeech(spokenText, targetCard.phraseAnglais)
        userSpokenResult.value = evaluation
        viewModelScope.launch {
            repository.updateSpeakingScore(evaluation.accuracyPercent)
        }
    }

    fun updateDailyGoal(newGoal: Int) {
        viewModelScope.launch {
            repository.updateDailyGoal(newGoal)
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsHelper.shutdown()
    }
}
