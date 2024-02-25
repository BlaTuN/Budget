package com.example.budget.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moneybox_info")
data class MoneyboxDb(
    val alreadyHave: Int,
    val amount: Int,
    val dateTo: String,
    val isArchived: Boolean,
    val title: String,
    val unit: String = "₽",
    val progress: Float,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
