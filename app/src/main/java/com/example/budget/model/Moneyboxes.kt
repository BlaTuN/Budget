package com.example.budget.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Moneyboxes(
    @Json(name = "moneyboxes")
    val moneyboxes: List<Moneybox>
)