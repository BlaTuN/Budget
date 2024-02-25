package com.example.budget.domain

import com.example.budget.data.MainRepository
import com.example.budget.model.MoneyboxDb

class SaveMoneyboxUseCase(private val mainRepository: MainRepository) {
    suspend fun saveMoneybox(moneyboxDb: MoneyboxDb) = mainRepository.saveMoneybox(moneyboxDb)
}