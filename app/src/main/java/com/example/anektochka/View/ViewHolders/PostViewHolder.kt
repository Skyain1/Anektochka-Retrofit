package com.example.anektochka.View.ViewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anektochka.Model.Item.Post
import com.example.anektochka.R
import com.example.anektochka.View.Listeners.Delete_Listener
import com.example.anektochka.databinding.ViewholderPostBinding

class PostViewHolder (private val binding: ViewholderPostBinding ,val inflater: LayoutInflater) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post, deleteListener: Delete_Listener){
        binding.title.text = post.type
        binding.setup.text = post.setup
        binding.punch.text = post.punchline

        val animation = AnimationUtils.loadAnimation(inflater.context, R.anim.fade_in)
        binding.root.startAnimation(animation)

        val popupView = inflater.inflate(R.layout.popup_layout, null)
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        val enterAnimation = AnimationUtils.loadAnimation(inflater.context, R.anim.popup_enter_animation)
        popupView.startAnimation(enterAnimation)

        val delete = popupView.findViewById<TextView>(R.id.delete)


        delete.setOnClickListener {
            deleteListener.onPostDeleted(post)
            popupWindow.dismiss()
        }

        binding.more.setOnClickListener {
            popupWindow.showAsDropDown(binding.more)
        }
    }
}