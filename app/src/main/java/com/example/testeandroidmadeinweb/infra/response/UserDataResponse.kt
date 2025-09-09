package com.example.testeandroidmadeinweb.infra.response

//Classe de resposta do retrofit
data class UserDataResponse(
    //Cada campo poderia ter um Serializable do retrofit para fazer o parse do json
    val userId: String,
    val authorizedToken: String,
    val email: String,
)


