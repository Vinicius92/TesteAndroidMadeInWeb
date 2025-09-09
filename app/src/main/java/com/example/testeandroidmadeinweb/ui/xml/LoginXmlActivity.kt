package com.example.testeandroidmadeinweb.ui.xml

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.testeandroidmadeinweb.R
import com.example.testeandroidmadeinweb.databinding.ActivityLoginXmlBinding
import com.example.testeandroidmadeinweb.ui.mvvm.LoginXmlViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginXmlActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginXmlBinding
    private val vm: LoginXmlViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginXmlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindActions()
        observeState()

    }

    private fun bindActions() = with(binding) {
        etEmail.doOnTextChanged { text, _, _, _ -> vm.onEmailChanged(text?.toString().orEmpty()) }
        etPassword.doOnTextChanged { text, _, _, _ -> vm.onPasswordChanged(text?.toString().orEmpty()) }

        btnLogin.setOnClickListener { vm.onLoginClicked() }

        tvSignUp.setOnClickListener {
            Toast.makeText(this@LoginXmlActivity, getString(R.string.label_register), Toast.LENGTH_SHORT).show()
        }

        tvForgot.setOnClickListener {
            Toast.makeText(this@LoginXmlActivity, getString(R.string.label_forgot_password), Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeState() {
        vm.state.observe(this) { st ->
            binding.progress.visibility = if (st.isLoading) View.VISIBLE else View.GONE
            binding.formContainer.setEnabledDeep(!st.isLoading)
            binding.tilPassword.isEndIconVisible = !st.isLoading

            if (st.error.isNullOrBlank()) {
                binding.tvError.visibility = View.GONE
            } else {
                binding.tvError.text = st.error
                binding.tvError.visibility = View.VISIBLE
            }

            if (st.success) {
                Toast.makeText(this, getString(R.string.label_login_success), Toast.LENGTH_SHORT).show()
                vm.consumeSuccess()
            }
        }

    }

    private fun View.setEnabledDeep(enabled: Boolean) {
        isEnabled = enabled
        if (this is ViewGroup) {
            for (i in 0 until childCount) {
                getChildAt(i).setEnabledDeep(enabled)
            }
        }
    }

}
