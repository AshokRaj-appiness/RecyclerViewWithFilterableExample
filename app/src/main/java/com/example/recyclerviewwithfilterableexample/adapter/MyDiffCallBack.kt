package com.example.recyclerviewwithfilterableexample.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.recyclerviewwithfilterableexample.model.Todo

class MyDiffCallBack(val newList:ArrayList<Todo>,val oldList:ArrayList<Todo> ) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition))
    }

}