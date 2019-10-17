package com.example.recyclerviewwithfilterableexample.network

import com.example.recyclerviewwithfilterableexample.model.Todo
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("todos")
    fun getTodos(): Single<List<Todo>>

}