package com.ufscar.mobile.todolist.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://dl.dropboxusercontent.com/s/bgxwwd524fr4mb8/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createTodoService() = retrofit.create(TodoService::class.java)
}