package com.example.testeandroidmadeinweb.ui.mvvm

data class LoginXmlUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)
