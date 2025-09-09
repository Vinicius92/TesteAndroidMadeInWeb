package com.example.testeandroidmadeinweb.domain.usecases

import com.example.testeandroidmadeinweb.domain.errors.PasswordError
import com.example.testeandroidmadeinweb.domain.model.PasswordResult

class IsPasswordValidUseCase {

    operator fun invoke(password: String): PasswordResult {
        return when {
            password.length < 4 ->
                PasswordResult.Failure(PasswordError.TooShort)
            !password.any { it.isUpperCase() } ->
                PasswordResult.Failure(PasswordError.MissingUppercase)
            !password.any { it.isDigit() } ->
                PasswordResult.Failure(PasswordError.MissingNumber)
            !password.any { !it.isLetterOrDigit() } ->
                PasswordResult.Failure(PasswordError.MissingSpecialChar)
            else -> PasswordResult.Success
        }
    }
}
