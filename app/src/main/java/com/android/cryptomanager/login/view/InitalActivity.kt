package com.android.cryptomanager.login.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.cryptomanager.databinding.InitialActivityBinding

class InitialActivity : AppCompatActivity() {

    private lateinit var binding: InitialActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InitialActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
