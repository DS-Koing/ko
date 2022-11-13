package com.example.koing

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.example.koing.databinding.FragmentMyPageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentMyPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentMyPage : Fragment() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding:FragmentMyPageBinding
    private val fragmentSetting by lazy { SettingsFragment() }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        // (이재현) Settings 버튼
        binding.mypageBtnSetting.setOnClickListener {
            val intent = Intent(context, SettingsActivity::class.java)
            startActivity(intent)
        }

        // (이재현) 찜한 상품 눌렀을 때
        binding.mypageBtnFav.setOnClickListener {
            val intent = Intent(context, FavoriteActivity::class.java)
            startActivity(intent)
        }

        // (이재현) 주문내역 눌렀을 때
        binding.mypageBtnHistory.setOnClickListener {
            val intent = Intent(context, HistoryActivity::class.java)
            startActivity(intent)
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var name = sharedPreferences.getString("username", "")
        if(name.isNullOrEmpty())
            name = "닉네임이 설정되지 않았습니다."
        binding.mypageTvName.text = name

        binding.mypageBtnLogout.setOnClickListener {
            Auth.auth.signOut()
            Auth.email = null
        }

        binding.mypageBtnDelAccount.setOnClickListener {
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var name = sharedPreferences.getString("username", "")
        if(name.isNullOrEmpty())
            name = "닉네임이 설정되지 않았습니다."
        binding.mypageTvName.text = name
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentMyPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentMyPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}