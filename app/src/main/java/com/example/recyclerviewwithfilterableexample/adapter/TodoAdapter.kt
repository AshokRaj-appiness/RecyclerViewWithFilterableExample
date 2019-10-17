package com.example.recyclerviewwithfilterableexample.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithfilterableexample.R
import com.example.recyclerviewwithfilterableexample.model.Todo

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.ViewHolder>(),Filterable {


    val todoFinalList = ArrayList<Todo>()
    val todoFilteredList = ArrayList<Todo>()
    lateinit var context: Context

    fun setData(myList:ArrayList<Todo>){
        todoFinalList.addAll(myList)
        todoFilteredList.addAll(myList)
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val tv_status = itemView.findViewById(R.id.tv_status) as TextView
        val tv_userId = itemView.findViewById(R.id.tv_userId) as TextView
        val tv_title = itemView.findViewById(R.id.tv_title) as TextView
    }

    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        holder.tv_userId.text =  todoFinalList.get(position).id.toString()
        holder.tv_title.text =  todoFinalList.get(position).title
        holder.tv_status.text =  todoFinalList.get(position).completed.toString()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder {
        context = parent.context
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false))
    }

    override fun getItemCount(): Int {
        return todoFinalList.size
    }

    override fun getFilter(): Filter {
        return object :Filter(){

            override fun performFiltering(p0: CharSequence?): FilterResults {
                val searchableString = p0.toString().toLowerCase()
                val todoFilteredList1 = ArrayList<Todo>()
                for(item in todoFilteredList){
                    if(item.title.contains(searchableString)){
                        todoFilteredList1.add(item)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = todoFilteredList1
                return filterResults

            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                val result = DiffUtil.calculateDiff(MyDiffCallBack(p1?.values as ArrayList<Todo>,todoFinalList))
                result.dispatchUpdatesTo(this@TodoAdapter)
                todoFinalList.clear()
                todoFinalList.addAll(p1?.values as ArrayList<Todo>)
            }



        }
    }


}