package com.ufscar.mobile.todolist.network

import com.ufscar.mobile.todolist.entidades.ItemList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoService {
    @GET("todo.json")
    fun getAll(): Call<ItemList>
}