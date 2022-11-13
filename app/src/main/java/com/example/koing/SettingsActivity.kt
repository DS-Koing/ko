package com.example.koing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.koing.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        val context = this

    }
}