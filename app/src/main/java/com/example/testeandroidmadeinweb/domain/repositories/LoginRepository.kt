package com.example.testeandroidmadeinweb.domain.repositories

import com.example.testeandroidmadeinweb.domain.model.UserData

interface LoginRepository {
    suspend fun login(email: String, password: String): UserData
}