package com.example.anektochka.View.Adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.anektochka.Model.Item.Post

class MyDiffUtil(val oldList: MutableList<Post>, val newList: MutableList<Post>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
       return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].id !=newList[newItemPosition].id->{
                false
            }
            oldList[oldItemPosition].setup !=newList[newItemPosition].setup->{
                false
            }
            oldList[oldItemPosition].punchline !=newList[newItemPosition].punchline->{
                false
            }
            oldList[oldItemPosition].type !=newList[newItemPosition].type->{
                false
            }
            else->true
        }
    }
}