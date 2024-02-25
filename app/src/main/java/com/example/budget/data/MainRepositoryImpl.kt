package com.example.budget.data

import com.example.budget.model.Moneybox
import com.example.budget.model.MoneyboxDb
import com.example.budget.service.MainApi
import com.example.budget.service.MoneyboxDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepositoryImpl(
    private val mainApi: MainApi,
    private val moneyboxDao: MoneyboxDao,
) : MainRepository {
    override suspend fun getMoneyboxList() = mainApi.getMoneyboxList()


    override fun getSaveMoneyboxList() = moneyboxDao.getAllCity()

    override suspend fun saveMoneybox(moneybox: MoneyboxDb) = moneyboxDao.upsertMoneybox(moneybox)

    override suspend fun saveMoneyboxList(moneyboxList: List<MoneyboxDb>) =
        moneyboxDao.upsertMoneyboxList(moneyboxList)


}