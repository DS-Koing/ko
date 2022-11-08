package com.example.koing

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.koing.databinding.ActivityMainBinding
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    private val fragmentOrder by lazy { FragmentOrder() }
    private val fragmentMyPage by lazy { FragmentMyPage() }
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences

    var getManagerApiBtn: Button? = null
    var setManagerApiBtn: Button? = null

    var userRetrofitInterface: UserRetrofitInterface? = null
    var menuRetrofitInterface: MenuRetrofitInterface? = null
    var s_menuRetrofitInterface: S_MenuRetrofitInterface? = null
    var orderRetrofitInterface: OrderRetrofitInterface? = null
    var cartRetrofitInterface: CartRetrofitInterface? = null

    var callUser: Call<User>? = null
    var callCart: Call<Cart>? = null
    var callMenu: Call<Menu>? = null
    var callSMenu: Call<S_Menu>? = null
    var callOrder: Call<Order>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var context = this@MainActivity

        initNavigationBar()

        // if 로그인 x
        val intent = Intent(context, AuthActivity::class.java)
        startActivity(intent)

        //백엔드(박지호)
        var retrofitClient: RetrofitClient? = RetrofitClient.getInstance()
        var userRetrofitInterface = RetrofitClient.getUserRetrofitInterface()
        var s_menuRetrofitInterface = RetrofitClient.getS_MenuRetrofitInterface()
        var orderRetrofitInterface = RetrofitClient.getOrderRetrofitInterface()
        var cartRetrofitInterface = RetrofitClient.getCartRetrofitInterface()


        (1) Manager_add 페이지 [프론트 아직 완성 X]
        getManagerApiBtn = findViewById(R.id.getApiButton) as android.widget.Button?
        var foodnameText = findViewById(R.id.getName) as TextView //음식 메뉴
        var foodpriceText = findViewById(R.id.getPrice) as TextView //음식 가격

        getApiBtn?.setOnClickListener {
            callSMenu = orderRetrofitInterface.user
            (callSMenu as Call<S_Menu>?)?.clone()?.enqueue(object : Callback<S_Menu> {
                override fun onResponse(call: Call<S_Menu>, response: Response<S_Menu>) {
                    if (response.isSuccessful) {
                        foodnameText.text = response.body()!!.SMenu_NM
                        foodpriceText.text = Integer.toString(response.body()!!.SMenu_PR)
                    }
                }

                override fun onFailure(call: Call<S_Menu>, t: Throwable) {
                    Log.e("retrofit 연동", "실패")
                    t.printStackTrace()
                }
            })


        }

        private fun initNavigationBar() {
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

        private fun changeFragment(fragment: Fragment) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_layout, fragment)
                .commit()
        }

    }
}