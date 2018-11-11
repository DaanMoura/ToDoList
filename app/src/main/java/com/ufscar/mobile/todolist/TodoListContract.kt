package com.ufscar.mobile.todolist

import android.content.Context

interface TodoListContract {

    interface View {
        fun exibeLista(list: ArrayList<Item>)
    }

    interface Presenter {
        fun onAtualizaLista(context: Context)
        fun onDeletaItem(context: Context, item: Item)
    }
}