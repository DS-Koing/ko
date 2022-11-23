package com.example.koing

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.koing.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator



class FragmentOrder : Fragment(){

    private lateinit var binding: ActivityMainBinding
    private val tabTitleArray = arrayOf(
        "Tab1",
        "Tab2",
        "Tab3",
        "Tab4",
        "Tab4"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_order, container, false)

}




}