package com.example.testeandroidmadeinweb.domain.errors

sealed class PasswordError : Throwable() {
    data object TooShort : PasswordError()
    data object MissingUppercase : PasswordError()
    data object MissingNumber : PasswordError()
    data object MissingSpecialChar : PasswordError()
}
