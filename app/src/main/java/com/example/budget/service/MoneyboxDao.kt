package com.example.budget.service

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.budget.model.Moneybox
import com.example.budget.model.MoneyboxDb
import kotlinx.coroutines.flow.Flow

@Dao
interface MoneyboxDao {

    @Upsert
    suspend fun upsertMoneyboxList(moneyboxList: List<MoneyboxDb>)

    @Upsert
    suspend fun upsertMoneybox(moneybox: MoneyboxDb)

    @Query("SELECT * FROM moneybox_info")
    fun getAllCity(): Flow<List<MoneyboxDb>>

}
