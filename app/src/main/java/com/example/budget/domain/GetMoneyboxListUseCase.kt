package com.example.budget.domain

import com.example.budget.data.MainRepository

class GetMoneyboxListUseCase(private val mainRepository: MainRepository) {
   suspend fun getMoneyboxList() = mainRepository.getMoneyboxList()
}