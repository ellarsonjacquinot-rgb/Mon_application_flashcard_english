package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.FlashcardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {
    @Query("SELECT * FROM flashcards ORDER BY id ASC")
    fun getAllFlashcards(): Flow<List<FlashcardEntity>>

    @Query("SELECT * FROM flashcards WHERE isFavorite = 1 ORDER BY id DESC")
    fun getFavoriteFlashcards(): Flow<List<FlashcardEntity>>

    @Query("SELECT * FROM flashcards WHERE nextReviewDate <= :currentTime ORDER BY nextReviewDate ASC")
    fun getDueFlashcards(currentTime: Long): Flow<List<FlashcardEntity>>

    @Query("SELECT * FROM flashcards WHERE phraseMalagasy LIKE '%' || :query || '%' OR phraseAnglais LIKE '%' || :query || '%' OR vocabulaire LIKE '%' || :query || '%'")
    fun searchFlashcards(query: String): Flow<List<FlashcardEntity>>

    @Query("SELECT * FROM flashcards WHERE (:niveau IS NULL OR niveau = :niveau) AND (:theme IS NULL OR theme = :theme) ORDER BY id ASC")
    fun getFilteredFlashcards(niveau: String?, theme: String?): Flow<List<FlashcardEntity>>

    @Query("SELECT COUNT(*) FROM flashcards")
    suspend fun getFlashcardCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(flashcards: List<FlashcardEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flashcard: FlashcardEntity)

    @Update
    suspend fun update(flashcard: FlashcardEntity)

    @Query("SELECT * FROM flashcards WHERE id = :id")
    suspend fun getFlashcardById(id: Int): FlashcardEntity?
}
