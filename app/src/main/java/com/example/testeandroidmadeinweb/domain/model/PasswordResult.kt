package com.example.testeandroidmadeinweb.domain.model

import com.example.testeandroidmadeinweb.domain.errors.PasswordError

sealed class PasswordResult {
    data object Success : PasswordResult()
    data class Failure(val error: PasswordError) : PasswordResult()
}
