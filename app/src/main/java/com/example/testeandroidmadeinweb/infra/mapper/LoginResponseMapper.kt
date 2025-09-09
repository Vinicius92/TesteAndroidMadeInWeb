package com.example.testeandroidmadeinweb.infra.mapper

import com.example.testeandroidmadeinweb.domain.model.UserData
import com.example.testeandroidmadeinweb.infra.response.UserDataResponse

class LoginResponseMapper {
    fun transform(response: UserDataResponse) = with(response) {
        UserData(
            userId = userId,
            authorizedToken = authorizedToken,
            email = email,
        )
    }
}
