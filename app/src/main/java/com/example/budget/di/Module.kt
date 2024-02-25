package com.example.budget.di

import com.example.budget.data.MainRepository
import com.example.budget.data.MainRepositoryImpl
import com.example.budget.domain.GetMoneyboxListUseCase
import com.example.budget.domain.GetSaveMoneyboxListUseCase
import com.example.budget.domain.SaveMoneyboxListUseCase
import com.example.budget.domain.SaveMoneyboxUseCase
import com.example.budget.viewmodel.MainViewModel
import com.example.budget.viewmodel.SecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    factory<MainRepository> { MainRepositoryImpl(mainApi = get(), moneyboxDao = get()) }
    factory { GetMoneyboxListUseCase(mainRepository = get()) }
    factory { GetSaveMoneyboxListUseCase(mainRepository = get()) }
    factory { SaveMoneyboxListUseCase(mainRepository = get()) }
    factory { SaveMoneyboxUseCase(mainRepository = get()) }
    viewModel { SecondViewModel(saveMoneyboxUseCase = get()) }
    viewModel {
        MainViewModel(
            getMoneyboxListUseCase = get(),
            getSaveMoneyboxListUseCase = get(),
            saveMoneyboxListUseCase = get()
        )
    }
}