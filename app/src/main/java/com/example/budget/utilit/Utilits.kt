package com.example.budget.utilit

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toTimeFormat(format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.GERMAN)
    val date = Date(this)
    return dateFormat.format(date)

}

