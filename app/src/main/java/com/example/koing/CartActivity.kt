package com.example.koing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.koing.databinding.ManagerBinding

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun settingButton(){
        //결제 버튼 클릭시 check 파일로 이동
        val button = findViewById<Button>(R.id.btn4)
        button.setOnClickListener{
            val intent= Intent(this, CheckActivity::class.java)
            startActivity(intent)
        }
    }

}