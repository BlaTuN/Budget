package com.example.budget.domain

import com.example.budget.data.MainRepository

class GetSaveMoneyboxListUseCase(private val mainRepository: MainRepository) {
    fun getSaveMoneyboxList() = mainRepository.getSaveMoneyboxList()
}