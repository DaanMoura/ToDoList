package com.ufscar.mobile.todolist.cenarios.todo_list

import android.content.Context
import android.view.View
import com.ufscar.mobile.todolist.entidades.ItemList
import com.ufscar.mobile.todolist.network.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoListApiPresenter(val view: TodoListContract.View): TodoListContract.ApiPresenter {
    override fun onAtualizaLista(context: Context) {
        val todoService = RetrofitInitializer().createTodoService()
        val call = todoService.getAll()

        call.enqueue(object : Callback<ItemList> {
            override fun onFailure(call: Call<ItemList>, t: Throwable) {
                view.showMessage("Falha na conexão. Verifique o acesso a internet")
            }

            override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                if (response?.body() != null) {
                    try {
                        view.exibeLista(response.body()!!.todoList)
                    } catch (e: Error) {
                        view.showMessage(e.message!!)
                    }
                } else {
                    view.showMessage("Nada a exibir")
                }
            }

        })
    }
}