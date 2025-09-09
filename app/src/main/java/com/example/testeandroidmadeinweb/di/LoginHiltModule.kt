package com.example.testeandroidmadeinweb.di

import com.example.testeandroidmadeinweb.domain.repositories.LoginRepository
import com.example.testeandroidmadeinweb.domain.usecases.IsEmailValidUseCase
import com.example.testeandroidmadeinweb.domain.usecases.IsPasswordValidUseCase
import com.example.testeandroidmadeinweb.domain.usecases.PerformLoginUseCase
import com.example.testeandroidmadeinweb.infra.LoginRepositoryImpl
import com.example.testeandroidmadeinweb.infra.datasource.LoginDataSource
import com.example.testeandroidmadeinweb.infra.mapper.LoginResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginHiltModule {

    @Provides
    fun provideLoginDataSource() = LoginDataSource()
    @Provides
    fun provideLoginResponseMapper() = LoginResponseMapper()

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginDataSource: LoginDataSource,
        mapper: LoginResponseMapper
    ): LoginRepository = LoginRepositoryImpl(
        loginDataSource = loginDataSource,
        mapper = mapper,
    )

    @Provides fun provideValidateEmailUseCase() = IsEmailValidUseCase()
    @Provides fun provideIsPasswordValidUseCase() = IsPasswordValidUseCase()

    @Provides
    fun providePerformLoginUseCase(
        repo: LoginRepository,
        validateEmail: IsEmailValidUseCase,
        isPasswordValid: IsPasswordValidUseCase
    ) = PerformLoginUseCase(repo, validateEmail, isPasswordValid)
}