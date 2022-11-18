package com.example.koing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.koing.Auth.Companion.auth
import com.example.koing.databinding.ManagerAddBinding
import com.example.koing.databinding.ManagerBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = Firebase.auth

        binding.managerBtnAdd.setOnClickListener {
            Log.d("mobile", "add btn clicked")
            val intent = Intent(baseContext, ManagerAddActivity::class.java)
            startActivity(intent)
        }

        binding.managerBtnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(baseContext, AuthActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(baseContext, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}