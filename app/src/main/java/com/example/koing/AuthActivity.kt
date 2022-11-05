package com.example.koing

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.koing.databinding.ActivityAuthBinding
import com.example.koing.databinding.ActivityMainBinding
import java.util.regex.Pattern

class AuthActivity : AppCompatActivity() {
    lateinit var binding : ActivityAuthBinding
    var emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@duksung.ac.kr"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.authBtnLogin.setOnClickListener {
            val email = binding.authEtEmail.text.toString()
            val password = binding.authEtPassword.text.toString()

            // 로그인 검증
            finish()
        }

        binding.authEtEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkEmail()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmail()
            }
        })

        binding.authTvSignup.setOnClickListener{
            changeVisibility("signup")
        }

        binding.authTvLogin.setOnClickListener {
            changeVisibility("login")
        }

        binding.authBtnSignup.setOnClickListener {
            val email = binding.authEtEmail.text.toString()
            val password = binding.authEtPassword.text.toString()
        }
    }

    fun changeVisibility(mode:String){
        if(mode.equals("login")){ // 로그인 화면
            binding.run {
                authBtnLogin.visibility = View.VISIBLE
                authBtnSignup.visibility = View.GONE
                authTvDetail1.visibility = View.VISIBLE
                authTvDetail2.visibility = View.GONE
                authBtnLogin.visibility = View.VISIBLE
                authBtnSignup.visibility = View.GONE
            }
        }
        else if(mode.equals("signup")){
            binding.run {
                authBtnLogin.visibility = View.GONE
                authBtnSignup.visibility = View.VISIBLE
                authTvDetail1.visibility = View.GONE
                authTvDetail2.visibility = View.VISIBLE
                authBtnLogin.visibility = View.GONE
                authBtnSignup.visibility = View.VISIBLE
            }
        }
    }

    fun checkEmail():Boolean{
        var email = binding.authEtEmail.text.toString().trim() //공백제거
        val p = Pattern.matches(emailValidation, email) // 서로 패턴이 맞닝?
        if (p) {
            //이메일 형태가 정상일 경우
            binding.authEtEmail.setTextColor(getResources().getColor(R.color.white))
            return true
        } else {
            binding.authEtEmail.setTextColor(getResources().getColor(R.color.main))
            //또는 questionEmail.setTextColor(R.color.red.toInt())
            return false
        }
    }
}