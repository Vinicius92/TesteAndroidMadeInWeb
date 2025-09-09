package com.example.testeandroidmadeinweb.ui.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.testeandroidmadeinweb.ui.mvi.LoginComposeViewModel
import com.example.testeandroidmadeinweb.ui.mvi.LoginEffect
import com.example.testeandroidmadeinweb.ui.mvi.LoginIntent
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: LoginComposeViewModel = getViewModel() // Koin
            val state by vm.state.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                vm.effects.collect { eff ->
                    when (eff) {
                        LoginEffect.Success ->
                            Toast.makeText(context, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            MaterialTheme {
                LoginScreen(
                    state = state,
                    onEmail = { vm.dispatch(LoginIntent.EmailChanged(it)) },
                    onPassword = { vm.dispatch(LoginIntent.PasswordChanged(it)) },
                    onSubmit = { vm.dispatch(LoginIntent.Submit) },
                    onSignUp = {
                        Toast.makeText(context, "clicou no cadastre-se", Toast.LENGTH_SHORT).show()
                    },
                    onForgot = {
                        Toast.makeText(context, "clicou em esqueci minha senha", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}
