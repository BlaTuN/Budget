package com.example.budget.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.budget.model.Moneybox
import com.example.budget.model.MoneyboxDb
import com.example.budget.service.MoneyboxDao


@Database(
    entities = [
        MoneyboxDb::class
    ],
    version = 2,
    exportSchema = true
)
abstract class MoneyboxDatabase : RoomDatabase() {

    abstract val dao: MoneyboxDao

}
