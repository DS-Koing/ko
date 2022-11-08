package com.example.koing

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.koing.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val fragmentOrder by lazy { FragmentOrder() }
    private val fragmentMyPage by lazy { FragmentMyPage() }
    lateinit var binding : ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var context = this@MainActivity

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
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.order -> {
                        changeFragment(fragmentOrder)
                    }
                    R.id.mypage -> {
                        changeFragment(fragmentMyPage)
                    }
                }
                true
            }
            selectedItemId = R.id.order
        }
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_layout, fragment)
            .commit()
    }




}