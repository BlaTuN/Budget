package com.example.budget.service

import com.example.budget.model.Moneybox
import com.example.budget.model.Moneyboxes
import retrofit2.http.GET

interface MainApi {

    @GET("a3ffe944-a095-4ba1-9092-7feff03e5c09")
    suspend fun getMoneyboxList():Moneyboxes
}
