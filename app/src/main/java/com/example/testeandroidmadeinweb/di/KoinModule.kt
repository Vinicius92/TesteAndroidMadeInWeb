package com.example.testeandroidmadeinweb.di

import com.example.testeandroidmadeinweb.domain.repositories.LoginRepository
import com.example.testeandroidmadeinweb.domain.usecases.IsEmailValidUseCase
import com.example.testeandroidmadeinweb.domain.usecases.IsPasswordValidUseCase
import com.example.testeandroidmadeinweb.domain.usecases.PerformLoginUseCase
import com.example.testeandroidmadeinweb.infra.LoginRepositoryImpl
import com.example.testeandroidmadeinweb.infra.datasource.LoginDataSource
import com.example.testeandroidmadeinweb.infra.mapper.LoginResponseMapper
import com.example.testeandroidmadeinweb.ui.mvi.LoginComposeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { LoginDataSource() }
    factory { LoginResponseMapper() }
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }

    factory { IsEmailValidUseCase() }
    factory { IsPasswordValidUseCase() }
    factory { PerformLoginUseCase(get(), get(), get()) }

    viewModel { LoginComposeViewModel(get()) }
}
