package com.example.testeandroidmadeinweb.domain.errors

sealed class LoginError: Throwable() {
    data object EmptyEmailOrPassword : LoginError()
    data object InvalidEmail : LoginError()
    data class InvalidPassword(val reason: PasswordError) : LoginError()
    data object Unauthorized : LoginError()
    data object Unknown: LoginError()
}
