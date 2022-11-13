package com.example.koing

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.koing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val fragmentOrder by lazy { FragmentOrder() }
    private val fragmentMyPage by lazy { FragmentMyPage() }
    private val fragmentCart by lazy { FragmentCart() }
    lateinit var binding : ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var context = this@MainActivity

        // (이재현) 하단 네비게이션 바 설정
        initNavigationBar()

        // if 로그인 x
        val intent = Intent(context, AuthActivity::class.java)
        startActivity(intent)

    }

    private fun initNavigationBar(){
        binding.bottomNavbar.run {
            /* var badge = binding.bottomNavbar.getOrCreateBadge(R.id.order)
            badge.isVisible = true
            badge.number = 99 */
            // (이재현) 나브바에서 각 아이템 선택되었을 때
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.order -> {
                        changeFragment(fragmentOrder)
                    }
                    R.id.cart -> {
                        changeFragment(fragmentCart)
                    }
                    R.id.mypage -> {
                        changeFragment(fragmentMyPage)
                    }
                }
                true
            }
            // (이재현) 주문 페이지가 기본
            selectedItemId = R.id.order
        }
    }

    // (이재현) 프래그먼트 전환 함수
    private fun changeFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_layout, fragment)
            .commit()
    }





}