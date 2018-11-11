package com.ufscar.mobile.todolist

import android.content.Context
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdicionaItemPresenter(val view: AdicionaItemContract.View): AdicionaItemContract.Presenter {
    override fun onSalvaItem(context: Context, item: Item) {
        val itemDao: ItemDAO = AppDatabase.getInstance(context).itemDao()
        doAsync {
            itemDao.insert(item)
            uiThread {
                view.salvoComSucesso()
            }
        }
    }
}