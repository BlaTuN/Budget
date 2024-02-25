package com.example.budget.service

import com.example.budget.model.Moneybox

sealed interface MainScreenEvent {
    data object Success : MainScreenEvent

    data class Error(val error: String) : MainScreenEvent

    data object Loading : MainScreenEvent
}