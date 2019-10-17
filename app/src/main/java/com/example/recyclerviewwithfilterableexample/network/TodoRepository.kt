package com.example.recyclerviewwithfilterableexample.network

import com.example.recyclerviewwithfilterableexample.model.Todo
import io.reactivex.Single

class TodoRepository(val baseuRL:String,val isDebugMode:Boolean):Repository(baseuRL,isDebugMode) {
    val retrofitInterface = retrofit.create(RetrofitInterface::class.java)
    inner class Result(val todoList:List<Todo>? = null,val errorMessage:String? = null){
        fun hasTodoList():Boolean{
            return todoList!=null && !todoList.isEmpty()
        }
        fun hasError():Boolean{
            return errorMessage!=null
        }
    }
    fun getTodos():Single<Result> = retrofitInterface.getTodos().map { Result(todoList = it) }.onErrorReturn { Result(errorMessage = it.message) }
}