package com.example.koing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int =5

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FragmentChild()
            1 -> FragmentChild()
            2-> FragmentChild()
            3-> FragmentChild()
            4-> FragmentToday()
            else -> FragmentToday()
        }

    }
}