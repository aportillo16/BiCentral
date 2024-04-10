package com.example.bicentral

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bicentral.databinding.LoginBiBinding

class login_bi : AppCompatActivity() {

    private lateinit var binding: LoginBiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginBiBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}