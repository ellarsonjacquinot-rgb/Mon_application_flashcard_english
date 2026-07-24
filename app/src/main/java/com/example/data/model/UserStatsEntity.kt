package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
data class UserStatsEntity(
    @PrimaryKey val id: Int = 1,
    val dailyGoal: Int = 10,
    val cardsReviewedToday: Int = 0,
    val lastActiveDate: String = "", // YYYY-MM-DD
    val streakDays: Int = 0,
    val totalReviews: Int = 0,
    val totalSpeakingAttempts: Int = 0,
    val totalSpeakingScoreSum: Int = 0
)
