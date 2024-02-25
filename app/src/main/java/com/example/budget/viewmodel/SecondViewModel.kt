package com.example.budget.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budget.domain.SaveMoneyboxUseCase
import com.example.budget.model.MoneyboxDb
import com.example.budget.utilit.Constant
import com.example.budget.utilit.toTimeFormat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class SecondViewModel(private val saveMoneyboxUseCase: SaveMoneyboxUseCase) : ViewModel() {

    private val _titleText = MutableStateFlow("")
    val titleText = _titleText.asStateFlow()

    private val _amountText = MutableStateFlow("")
    val amountText = _amountText.asStateFlow()

    private val _amountSaveText = MutableStateFlow("")
    val amountSaveText = _amountSaveText.asStateFlow()

    private val _isChecked = MutableStateFlow(false)
    val isChecked = _isChecked.asStateFlow()

    private val _dataText = MutableStateFlow(Calendar.getInstance().timeInMillis)
    val dataText = _dataText

    private val _isOpenDialog = MutableStateFlow(false)
    val isOpenDialog = _isOpenDialog

    fun onTitleChangeText(title: String) {
        _titleText.value = title
    }

    fun onAmountChangeText(amount: String) {
        _amountText.value = amount
    }

    fun onAmountSaveChangeText(amountSave: String) {
        _amountSaveText.value = amountSave
    }

    fun onIsCheckedChange(check: Boolean) {
        _isChecked.value = check
    }

    fun onDataTextChange(data: Long) {
        _dataText.value = data
    }

    fun onIsDialogOpenChange(isOpen: Boolean) {
        _isOpenDialog.value = isOpen
    }

    fun saveMoneybox() {
        viewModelScope.launch {
            var isArchive = false
            if (!isChecked.value){
                _amountSaveText.value = "0"
            }
            if (amountSaveText.value.toInt() >= amountText.value.toInt()) {
                isArchive = true
            }
            val process = amountSaveText.value.toFloat()/amountText.value.toFloat()
            val moneybox = MoneyboxDb(
                alreadyHave = amountSaveText.value.toInt(),
                amount = amountText.value.toInt(),
                dateTo = "до ${dataText.value.toTimeFormat(Constant.DATA_FORMAT_SECOND_SCREEN)}",
                isArchived = isArchive,
                title = titleText.value,
                progress = process
            )
            saveMoneyboxUseCase.saveMoneybox(moneybox)
        }
    }
}