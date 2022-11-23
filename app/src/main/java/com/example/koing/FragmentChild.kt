package com.example.koing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import com.example.koing.databinding.ActivityAuthBinding.inflate
import com.example.koing.databinding.ActivityCartBinding.inflate
import com.example.koing.databinding.FragmentCartBinding.inflate
import com.example.koing.databinding.FragmentChildBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentChild.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentChild : Fragment() {

    lateinit var binding:FragmentChildBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentChildBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        return binding.root
    }


}