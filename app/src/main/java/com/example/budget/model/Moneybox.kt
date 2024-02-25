package com.example.budget.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Moneybox(
    @Json(name = "alreadyhave")
    val alreadyHave: Int,
    @Json(name = "amount")
    val amount: Int,
    @Json(name = "dateto")
    val dateTo: String,
    @Json(name = "isArchived")
    val isArchived: Boolean,
    @Json(name = "title")
    val title: String,
    @Json(name = "unit")
    val unit: String,
    val id: String,
)
