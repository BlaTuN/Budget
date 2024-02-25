package com.example.budget.di

import android.content.Context
import androidx.room.Room
import com.example.budget.db.MoneyboxDatabase
import com.example.budget.utilit.Constant
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun providerDatabase(context: Context) =
    Room.databaseBuilder(context, MoneyboxDatabase::class.java, Constant.DATABASE_NAME)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun providerDao(db: MoneyboxDatabase) = db.dao

val dBModule = module {
    single { providerDatabase(androidContext()) }
    single { providerDao(db = get()) }
}