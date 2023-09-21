package com.example.anektochka.View.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.anektochka.Model.Item.Post
import com.example.anektochka.View.Listeners.Swap_Listener
import com.example.anektochka.databinding.ViewholderChangerBinding

class ChangerViewHolder (private val binding: ViewholderChangerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post, listener: Swap_Listener){
        binding.btTopic.setOnClickListener {
            listener.onChapterClicked(post)
        }
    }

}