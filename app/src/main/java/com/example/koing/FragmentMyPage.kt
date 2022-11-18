package com.example.koing

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.example.koing.databinding.FragmentMyPageBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

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
    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser
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

    @SuppressLint("WrongThread")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        user = auth.currentUser!!
        binding.mypageTvEmail.text = user.email



        // (이재현) 프로필 사진 버튼
        binding.mypageBtnProfile.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }

            val ref = FirebaseStorage.getInstance().getReference()
            val bitmap = (binding.mypageBtnProfile.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val mountainsRef = ref.child(user.uid + "_profile")

            val uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener{
                Log.e("mobile", "실패")
            }.addOnSuccessListener {
                Log.e("mobile", "성공")
            }

            FirebaseStorage.getInstance().getReference().child(user.uid+"_profile").downloadUrl
                .addOnCompleteListener(OnCompleteListener<Uri> { task ->
                    if(task.isSuccessful){
                        Glide.with(this)
                            .load(task.result)
                            .into(binding.mypageBtnProfile)
                    } else{
                        Toast.makeText(
                            context,
                            task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

        }

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



        // (이재현) 로그아웃 버튼 눌렀을 때
        binding.mypageBtnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(context, AuthActivity::class.java)
            startActivity(intent)
            Toast.makeText(context, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
        }

        binding.mypageBtnDelAccount.setOnClickListener {
            auth.currentUser!!.delete()
            val intent = Intent(context, AuthActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
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

        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;

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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            binding.mypageBtnProfile.setImageURI(data?.data)
        }
    }
}

class FirebaseUtils {
    val fireStoreDatabase = FirebaseFirestore.getInstance()
}