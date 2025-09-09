package com.example.testeandroidmadeinweb.infra.datasource

import com.example.testeandroidmadeinweb.domain.errors.LoginError
import com.example.testeandroidmadeinweb.infra.response.UserDataResponse
import kotlinx.coroutines.delay

class LoginDataSource {
    suspend fun login(email: String, password: String): UserDataResponse {
        delay(1000)
        //Aqui poderiamos chamar o retrofit para fazer a requisição, injetando a API no construtor da classe
        if (email == "user@madeinweb.com.br" && password == "Test@123") {
            return UserDataResponse(
                userId = "1001",
                authorizedToken = "token",
                email = email
            )
        } else {
            throw LoginError.Unauthorized
        }
    }
}