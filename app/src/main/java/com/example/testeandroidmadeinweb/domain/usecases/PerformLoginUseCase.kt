package com.example.testeandroidmadeinweb.domain.usecases

import com.example.testeandroidmadeinweb.domain.errors.LoginError
import com.example.testeandroidmadeinweb.domain.model.PasswordResult
import com.example.testeandroidmadeinweb.domain.model.UserData
import com.example.testeandroidmadeinweb.domain.repositories.LoginRepository

class PerformLoginUseCase(
    private val loginRepository: LoginRepository,
    private val isEmailValidUseCase: IsEmailValidUseCase,
    private val isPasswordValid: IsPasswordValidUseCase,
) {
    suspend operator fun invoke(email: String, password: String): Result<UserData> {
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(LoginError.EmptyEmailOrPassword)
        }
        if (!isEmailValidUseCase(email)) {
            return Result.failure(LoginError.InvalidEmail)
        }
        when (val result = isPasswordValid(password)) {
            is PasswordResult.Failure -> {
                return Result.failure(LoginError.InvalidPassword(result.error))
            }
            PasswordResult.Success -> Unit
        }

        return try {
            val userData = loginRepository.login(email, password)
            Result.success(userData)
        } catch (e: LoginError) {
            Result.failure(e)
        }
    }
}