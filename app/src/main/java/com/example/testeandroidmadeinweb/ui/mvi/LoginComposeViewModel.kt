package com.example.testeandroidmadeinweb.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeandroidmadeinweb.domain.errors.LoginError
import com.example.testeandroidmadeinweb.domain.errors.PasswordError
import com.example.testeandroidmadeinweb.domain.usecases.PerformLoginUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginComposeViewModel(
    private val performLogin: PerformLoginUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _effects = Channel<LoginEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun dispatch(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> _state.update { it.copy(email = intent.value, error = null) }
            is LoginIntent.PasswordChanged -> _state.update { it.copy(password = intent.value, error = null) }
            LoginIntent.Submit -> submit()
            LoginIntent.ErrorConsumed -> _state.update { it.copy(error = null) }
        }
    }

    private fun submit() {
        val email = _state.value.email.trim()
        val pass = _state.value.password

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            performLogin(email, pass)
                .onSuccess {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    _effects.send(LoginEffect.Success)
                }
                .onFailure {
                    if (it is LoginError) {
                        treatLoginError(it)
                    } else {
                        treatLoginError(LoginError.Unknown)
                    }
                }
        }
    }

    private fun treatLoginError(error: LoginError) {
        val errorMessage = when(error) {
            LoginError.EmptyEmailOrPassword, LoginError.Unauthorized ->
                "Email ou senha inválidos, por favor preencha os campos novamente."
            LoginError.InvalidEmail ->
                "E-mail inválido, por favor digite um e-mail válido."
            is LoginError.InvalidPassword -> handlePasswordError(error.reason)
            LoginError.Unknown -> "Ocorreu um erro inesperado, tente novamente ou contate o suporte"
        }
        _state.update { it.copy(error = errorMessage, isLoading = false) }
    }

    private fun handlePasswordError(passwordError: PasswordError): String {
        return when(passwordError) {
            PasswordError.MissingNumber -> "Senha deve conter pelo menos um número"
            PasswordError.MissingSpecialChar -> "Senha deve conter pelo menos um caracter especial"
            PasswordError.MissingUppercase -> "Senha deve conter pelo menos uma letra maíuscula"
            PasswordError.TooShort -> "Senha deve ter no minimo 4 caracteres"
        }
    }
}
