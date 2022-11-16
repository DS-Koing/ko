package com.example.koing

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_activty)

        // Actionbar 제거
        supportActionBar?.hide()
        //스플래쉬 화면 (MainActivity가 아니라, AuthActivity로 경로 설정해야 회원가입 화면이 뜸)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            Intent(this, AuthActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }, 3000) // 3초 후(3000) 스플래시 화면을 닫습니다
    }
}