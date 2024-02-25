package com.example.budget.ui.navigation

sealed class Screen(val route: String) {
    data object MainScreen: Screen("main_screen")
    data object SecondScreen: Screen("second_screen")
}