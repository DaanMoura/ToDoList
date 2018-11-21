package com.ufscar.mobile.todolist.cenarios.todo_list

import android.content.Context
import com.ufscar.mobile.todolist.entidades.Item

interface TodoListContract {

    interface View {
        fun exibeLista(list: List<Item>?)
        fun showMessage(message: String)
    }

    interface Presenter {
        fun onAtualizaLista(context: Context)
        fun onDeletaItem(context: Context, item: Item)
    }

    interface ApiPresenter {
        fun onAtualizaLista(context: Context)
    }
}