package com.ufscar.mobile.todolist

import android.content.Context

interface AdicionaItemContract {
    interface View {
        fun salvoComSucesso()
    }

    interface Presenter {
        fun onSalvaItem(context: Context, item: Item)
    }
}