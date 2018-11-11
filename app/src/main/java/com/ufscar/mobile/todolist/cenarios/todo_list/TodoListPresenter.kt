package com.ufscar.mobile.todolist.cenarios.todo_list

import android.content.Context
import com.ufscar.mobile.todolist.database.AppDatabase
import com.ufscar.mobile.todolist.entidades.Item
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TodoListPresenter(val view: TodoListContract.View):
    TodoListContract.Presenter {

    override fun onAtualizaLista(context: Context) {
        val itemDao = AppDatabase.getInstance(context).itemDao()
        doAsync {
            val todoList = itemDao.getAll() as ArrayList<Item>
            uiThread {
                view.exibeLista(todoList)
            }
        }
    }

    override fun onDeletaItem(context: Context, item: Item) {
        val itemDao = AppDatabase.getInstance(context).itemDao()
        doAsync {
            itemDao.delete(item)
            uiThread {
                onAtualizaLista(context)
            }
        }
    }
}