package com.example.testeandroidmadeinweb.ui.mvi

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface LoginIntent {
    data class EmailChanged(val value: String) : LoginIntent
    data class PasswordChanged(val value: String) : LoginIntent
    data object Submit : LoginIntent
    data object ErrorConsumed : LoginIntent
}

sealed interface LoginEffect {
    data object Success : LoginEffect
}