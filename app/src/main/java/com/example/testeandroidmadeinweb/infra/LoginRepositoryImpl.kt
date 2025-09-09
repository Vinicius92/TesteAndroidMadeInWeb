package com.example.testeandroidmadeinweb.infra

import com.example.testeandroidmadeinweb.domain.model.UserData
import com.example.testeandroidmadeinweb.domain.repositories.LoginRepository
import com.example.testeandroidmadeinweb.infra.datasource.LoginDataSource
import com.example.testeandroidmadeinweb.infra.mapper.LoginResponseMapper

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource,
    private val mapper: LoginResponseMapper,
): LoginRepository {
    override suspend fun login(email: String, password: String): UserData {
        return mapper.transform(loginDataSource.login(email, password))
    }
}
