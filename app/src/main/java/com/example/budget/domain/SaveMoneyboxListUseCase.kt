package com.example.budget.domain

import com.example.budget.data.MainRepository
import com.example.budget.model.Moneybox
import com.example.budget.model.MoneyboxDb

class SaveMoneyboxListUseCase(private val mainRepository: MainRepository) {
    suspend fun saveMoneyboxList(moneyboxList: List<MoneyboxDb>) =
        mainRepository.saveMoneyboxList(moneyboxList)
}