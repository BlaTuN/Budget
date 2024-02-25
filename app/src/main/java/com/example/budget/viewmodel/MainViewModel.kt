package com.example.budget.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budget.domain.GetMoneyboxListUseCase
import com.example.budget.domain.GetSaveMoneyboxListUseCase
import com.example.budget.domain.SaveMoneyboxListUseCase
import com.example.budget.model.MoneyboxDb
import com.example.budget.service.MainScreenEvent
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(
    private val getMoneyboxListUseCase: GetMoneyboxListUseCase,
    getSaveMoneyboxListUseCase: GetSaveMoneyboxListUseCase,
    private val saveMoneyboxListUseCase: SaveMoneyboxListUseCase,
) : ViewModel() {
    var mainScreenEvent: MainScreenEvent by mutableStateOf(MainScreenEvent.Loading)
    val moneyboxSaveList = getSaveMoneyboxListUseCase.getSaveMoneyboxList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(50000L),
        initialValue = emptyList()
    )

    fun getMoneyboxList() {
        viewModelScope.launch {
            mainScreenEvent = MainScreenEvent.Loading
            mainScreenEvent = try {
                val moneyboxList = getMoneyboxListUseCase.getMoneyboxList()
                Log.e("moneybox list", "${moneyboxList.moneyboxes.size}")
                val moneyboxDb = mutableListOf<MoneyboxDb>()
                moneyboxList.moneyboxes.forEach { moneybox ->
                    val progress = moneybox.alreadyHave.toFloat() / moneybox.amount.toFloat()
                    moneyboxDb.add(
                        MoneyboxDb(
                            alreadyHave = moneybox.alreadyHave,
                            amount = moneybox.amount,
                            dateTo = "до ${moneybox.dateTo}",
                            isArchived = moneybox.isArchived,
                            title = moneybox.title,
                            unit = moneybox.unit,
                            progress = progress,
                            id = moneybox.id.toInt()
                        )
                    )
                }
                saveMoneyboxListUseCase.saveMoneyboxList(moneyboxDb)
                MainScreenEvent.Success
            } catch (e: Exception) {
                MainScreenEvent.Error(e.toString())
            } catch (e: HttpException) {
                MainScreenEvent.Error(e.toString())
            }
        }
    }
}