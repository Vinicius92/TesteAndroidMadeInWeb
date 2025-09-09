package com.example.testeandroidmadeinweb.domain.usecases

import android.util.Patterns

class IsEmailValidUseCase {
    operator fun invoke(email: String) =
        email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}