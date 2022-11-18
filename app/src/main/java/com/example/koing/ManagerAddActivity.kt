package com.example.koing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.koing.databinding.ManagerAddBinding

class ManagerAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ManagerAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.managerAddToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.managerAddBtnAdd.setOnClickListener {
            Toast.makeText(baseContext, "상품이 등록되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {}
        }
        return super .onOptionsItemSelected(item)
    }
}