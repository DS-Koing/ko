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

        // (이재현) 이메일 형식 검증을 위한 함수
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

        // (이재현) 회원가입 하기 눌렀을 때 -> 회원가입 화면으로 전환
        binding.authTvSignup.setOnClickListener{
            changeVisibility("signup")
        }

        // (이재현) 로그인 눌렀을 때 -> 로그인 화면으로 전환
        binding.authTvLogin.setOnClickListener {
            changeVisibility("login")
        }

        binding.authBtnSignup.setOnClickListener {
            // (이재현) 회원가입 버튼 눌렀을 때 edit text의 문자열 변수에 저장
            val email = binding.authEtEmail.text.toString()
            val password = binding.authEtPassword.text.toString()
        }
    }

    // (이재현) 로그인/회원가입 버튼 눌렀을 때 화면 전환하는 함수
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

    // (이재현) 이메일 패턴 검증하는 함수
    fun checkEmail():Boolean{
        var email = binding.authEtEmail.text.toString().trim() //공백제거
        val p = Pattern.matches(emailValidation, email) // 서로 패턴이 맞는지
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