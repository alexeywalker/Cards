package com.example.english.presentation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen(route = "home_screen")
    data object AddNewWord : Screen(route = "addNewWord_screen")
}
