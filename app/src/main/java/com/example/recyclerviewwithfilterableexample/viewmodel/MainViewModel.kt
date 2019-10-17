package com.example.recyclerviewwithfilterableexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerviewwithfilterableexample.base.BaseViewModel
import com.example.recyclerviewwithfilterableexample.model.Todo
import com.example.recyclerviewwithfilterableexample.network.TodoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel:BaseViewModel() {
    val todoListMutable = MutableLiveData<List<Todo>>()
    val errorMessage = MutableLiveData<String>()


    fun getTodoFromMainViewModel(baseUrl:String,isDebug:Boolean){
        val todoRepository = TodoRepository(baseUrl,isDebug)
        todoRepository.getTodos().subscribeOn(Schedulers.io()).doOnSubscribe { compositeDisposable.add(it) }.observeOn(AndroidSchedulers.mainThread()).subscribe {
            result ->
            when{
                result.hasTodoList() -> todoListMutable.value = result.todoList
                result.hasError() -> errorMessage.value = result.errorMessage
                else -> errorMessage.value = "Null Pointer error"
            }
        }

    }
    fun getTodoList():LiveData<List<Todo>>{
        return todoListMutable
    }

    fun getError():LiveData<String>{
        return errorMessage
    }
}