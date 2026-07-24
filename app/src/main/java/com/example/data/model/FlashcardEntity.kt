package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcards")
data class FlashcardEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val niveau: String, // "A2", "B1", "B2"
    val theme: String, // "Vie Quotidienne", "Opinions & Émotions", "Voyages & Transports", "Travail & Affaires", "Social & Conversations", "Urgences & Problèmes", "Récits & Expériences", "Projets & Hypothèses"
    val phraseMalagasy: String,
    val phraseAnglais: String,
    val prononciation: String,
    val vocabulaire: String,
    val grammaire: String,
    val variantes: String,
    val exemple: String,
    val audioKey: String = "",
    val difficulte: String = "Moyen", // "Facile", "Moyen", "Difficile"
    val isFavorite: Boolean = false,
    val srsInterval: Int = 0, // Days
    val srsEaseFactor: Float = 2.5f,
    val srsRepetitions: Int = 0,
    val nextReviewDate: Long = System.currentTimeMillis(),
    val mastered: Boolean = false,
    val lastReviewedDate: Long = 0L
)
