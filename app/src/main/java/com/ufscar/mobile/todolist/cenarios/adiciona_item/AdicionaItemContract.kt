package com.ufscar.mobile.todolist.cenarios.adiciona_item

import android.content.Context
import com.ufscar.mobile.todolist.entidades.Item

interface AdicionaItemContract {
    interface View {
        fun salvoComSucesso()
    }

    interface Presenter {
        fun onSalvaItem(context: Context, item: Item)
    }
}