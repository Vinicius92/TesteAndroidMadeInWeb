package com.example.testeandroidmadeinweb.ui.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeandroidmadeinweb.domain.errors.LoginError
import com.example.testeandroidmadeinweb.domain.errors.PasswordError
import com.example.testeandroidmadeinweb.domain.usecases.PerformLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginXmlViewModel @Inject constructor(
    private val performLogin: PerformLoginUseCase,
) : ViewModel() {
    private val _state = MutableLiveData(LoginXmlUiState())
    val state: LiveData<LoginXmlUiState> = _state

    fun onEmailChanged(email: String) {
        _state.value = _state.value?.copy(email = email, error = null)
    }

    fun onPasswordChanged(pw: String) {
        _state.value = _state.value?.copy(password = pw, error = null)
    }

    fun onLoginClicked() {
        val st = _state.value ?: return
        val email = st.email.trim()
        val pass = st.password

        viewModelScope.launch {
            _state.value = st.copy(isLoading = true)
           performLogin(email, pass)
               .onSuccess {
                   _state.postValue(_state.value?.copy(isLoading = false, success = true))
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
        _state.value = _state.value?.copy(error = errorMessage, isLoading = false, success = false)
    }

    private fun handlePasswordError(passwordError: PasswordError): String {
        return when(passwordError) {
            PasswordError.MissingNumber -> "Senha deve conter pelo menos um número"
            PasswordError.MissingSpecialChar -> "Senha deve conter pelo menos um caracter especial"
            PasswordError.MissingUppercase -> "Senha deve conter pelo menos uma letra maíuscula"
            PasswordError.TooShort -> "Senha deve ter no minimo 4 caracteres"
        }
    }

    fun consumeSuccess() {
        _state.value = _state.value?.copy(success = false)
    }
}
