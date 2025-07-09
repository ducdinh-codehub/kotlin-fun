package com.example.myapplication.ui.shared.context.auth

data class AuthData(
    val appAuthState: Boolean = false,
    val userName: String = "",
    val passWord: String = "",
)