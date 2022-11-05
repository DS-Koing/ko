package com.example.koing

import android.content.Context
import android.inputmethodservice.Keyboard
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koing.databinding.ItemFavoriteBinding

class FavViewHolder(val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root)
class FavAdapter(val binding: ItemFavoriteBinding, val datas:MutableList<String>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FavViewHolder).binding
        val model = datas!![position]
        binding.favMenu.text = model
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        FavViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }