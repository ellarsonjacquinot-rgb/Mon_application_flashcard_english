package com.example.data.speech

import java.util.Locale
import kotlin.math.max

data class WordMatchResult(
    val targetWord: String,
    val isMatched: Boolean
)

data class SpeakingEvaluation(
    val accuracyPercent: Int,
    val matchedWords: List<WordMatchResult>,
    val userSpokenText: String,
    val targetText: String,
    val feedbackMessage: String
)

object SpeechEvaluationUtils {

    fun evaluateSpeech(userSpoken: String, targetText: String): SpeakingEvaluation {
        val cleanTargetWords = targetText.lowercase(Locale.ROOT)
            .replace(Regex("[^a-z0-9\\s]"), "")
            .split(Regex("\\s+"))
            .filter { it.isNotEmpty() }

        val cleanUserWords = userSpoken.lowercase(Locale.ROOT)
            .replace(Regex("[^a-z0-9\\s]"), "")
            .split(Regex("\\s+"))
            .filter { it.isNotEmpty() }

        if (cleanTargetWords.isEmpty()) {
            return SpeakingEvaluation(
                accuracyPercent = 100,
                matchedWords = emptyList(),
                userSpokenText = userSpoken,
                targetText = targetText,
                feedbackMessage = "Excellente prononciation !"
            )
        }

        var matchCount = 0
        val wordResults = mutableListOf<WordMatchResult>()
        val userWordPool = cleanUserWords.toMutableList()

        for (targetWord in cleanTargetWords) {
            val indexInUser = userWordPool.indexOf(targetWord)
            if (indexInUser != -1) {
                matchCount++
                wordResults.add(WordMatchResult(targetWord, true))
                userWordPool.removeAt(indexInUser)
            } else {
                wordResults.add(WordMatchResult(targetWord, false))
            }
        }

        val accuracy = ((matchCount.toDouble() / cleanTargetWords.size.toDouble()) * 100).toInt()
            .coerceIn(0, 100)

        val feedback = when {
            accuracy >= 90 -> "Parfait ! Votre prononciation est très naturelle et fluide."
            accuracy >= 70 -> "Très bien ! Presque parfait. Entraînez-vous sur les mots en rouge."
            accuracy >= 50 -> "Bonne tentative ! Réécoutez l'audio pour affiner le rythme."
            else -> "Continuez à vous entraîner. Écoutez plusieurs fois l'audio et réessayez."
        }

        return SpeakingEvaluation(
            accuracyPercent = accuracy,
            matchedWords = wordResults,
            userSpokenText = userSpoken,
            targetText = targetText,
            feedbackMessage = feedback
        )
    }
}
