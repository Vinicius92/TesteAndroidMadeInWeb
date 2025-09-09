package com.example.testeandroidmadeinweb.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testeandroidmadeinweb.databinding.ActivityHomeBinding
import com.example.testeandroidmadeinweb.ui.compose.LoginComposeActivity
import com.example.testeandroidmadeinweb.ui.xml.LoginXmlActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnXml.setOnClickListener {
            startActivity(Intent(this, LoginXmlActivity::class.java))
        }
        binding.btnCompose.setOnClickListener {
            startActivity(Intent(this, LoginComposeActivity::class.java))
        }
    }
}