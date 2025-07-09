package com.example.myapplication.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

enum class AppScreen() {
    Login,
    Home,
    News,
    Settings
}

enum class InAppScreen(){
    Home,
    News,
    Settings,
}


@Composable
fun NavigationControl(){
    Scaffold {

    }
}