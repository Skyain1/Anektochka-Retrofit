package com.example.anektochka.View.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.anektochka.Model.Item.Post
import com.example.anektochka.databinding.ViewholderEmptyBinding

class EmptyViewHolder  (private val binding: ViewholderEmptyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post){

    }

}