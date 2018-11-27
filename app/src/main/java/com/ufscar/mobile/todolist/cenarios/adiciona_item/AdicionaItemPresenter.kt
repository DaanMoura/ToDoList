package com.ufscar.mobile.todolist.cenarios.adiciona_item

import android.content.Context
import com.ufscar.mobile.todolist.database.AppDatabase
import com.ufscar.mobile.todolist.entidades.Item
import com.ufscar.mobile.todolist.database.ItemDAO
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdicionaItemPresenter(val view: AdicionaItemContract.View):
    AdicionaItemContract.Presenter {
    override fun onSalvaItem(context: Context, item: Item) {
        val itemDao: ItemDAO = AppDatabase.getInstance(
            context
        ).itemDao()
        doAsync {
            itemDao.insert(item)
            uiThread {
                view.salvoComSucesso()
            }
        }
    }
}