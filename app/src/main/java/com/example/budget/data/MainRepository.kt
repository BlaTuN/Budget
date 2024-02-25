package com.example.budget.data

import com.example.budget.model.Moneybox
import com.example.budget.model.MoneyboxDb
import com.example.budget.model.Moneyboxes
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getMoneyboxList(): Moneyboxes

    fun getSaveMoneyboxList(): Flow<List<MoneyboxDb>>

    suspend fun saveMoneybox(moneybox: MoneyboxDb)

    suspend fun saveMoneyboxList(moneyboxList: List<MoneyboxDb>)

}