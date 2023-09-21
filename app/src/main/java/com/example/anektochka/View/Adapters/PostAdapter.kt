package com.example.anektochka.View.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.anektochka.Model.Item.Post
import com.example.anektochka.View.Listeners.Delete_Listener
import com.example.anektochka.View.Listeners.Swap_Listener
import com.example.anektochka.View.ViewHolders.ChangerViewHolder
import com.example.anektochka.View.ViewHolders.EmptyViewHolder
import com.example.anektochka.View.ViewHolders.PostViewHolder
import com.example.anektochka.databinding.ViewholderChangerBinding
import com.example.anektochka.databinding.ViewholderEmptyBinding
import com.example.anektochka.databinding.ViewholderPostBinding
import java.lang.IllegalArgumentException

class PostAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listener: Swap_Listener? = null
    private var deleteListener: Delete_Listener? = null
    private var oldPostList = emptyList<Post>().toMutableList()

    fun attachListener(listener: Swap_Listener) {
        this.listener = listener
    }

    fun attachDeleteListener(listener: Delete_Listener) {
        this.deleteListener = listener
    }

    fun deletePost(item: Post) {
        val pos = oldPostList.indexOf(item)
        oldPostList.removeAt(pos)
        notifyItemRemoved(pos)
    }

    companion object {
        const val SWAP_VIEW = -1
        const val POST_VIEW = 0
        const val EMPTY_VIEW = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            SWAP_VIEW -> ChangerViewHolder(
                ViewholderChangerBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )

            POST_VIEW -> PostViewHolder(
                ViewholderPostBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), inflater
            )

            EMPTY_VIEW -> EmptyViewHolder(
                ViewholderEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("invalid item type")
        }

    }

    override fun getItemCount(): Int {
        return oldPostList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (oldPostList[position].ViewType) {
            SWAP_VIEW -> (holder as ChangerViewHolder).bind(
                oldPostList[position],
                listener = listener!!
            )

            POST_VIEW -> (holder as PostViewHolder).bind(
                oldPostList[position],
                deleteListener = deleteListener!!
            )

            EMPTY_VIEW -> (holder as EmptyViewHolder).bind(oldPostList[position])
            else -> throw IllegalArgumentException("invalid item type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return oldPostList[position].ViewType
    }

    fun setData(newPostList: MutableList<Post>) {
        val diffUtil = MyDiffUtil(oldPostList, newPostList)
        val diifResults = DiffUtil.calculateDiff(diffUtil)
        oldPostList = newPostList
        diifResults.dispatchUpdatesTo(this)
    }
}