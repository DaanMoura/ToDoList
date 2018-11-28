package com.ufscar.mobile.todolist.cenarios.todo_list

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ufscar.mobile.todolist.entidades.Item
import com.ufscar.mobile.todolist.R
import com.ufscar.mobile.todolist.cenarios.adiciona_item.AdicionaItemFragment

class TodoListActivity : AppCompatActivity(), TodoListContract.View, TodoListFragment.onFragmentInteractionListener {
    private val ADICIONA_ITEM = 1
    private val NOVO_ITEM = "NovoItem"
    private val ITEM = "Item"
    var index: Int = -1

    var todoList = ArrayList<Item>()
    val presenter: TodoListContract.Presenter =
        TodoListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)
    }

    override fun exibeLista(list: ArrayList<Item>) {
        val fragmentTodoList = TodoListFragment.newInstance(list)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fmMaster, fragmentTodoList)
            .commit()
    }

    override fun onItemInteraction(item: Item) {
        val fragmentAddItem = AdicionaItemFragment.newInstance(item)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fmMaster, fragmentAddItem)
            .addToBackStack(null)
            .commit()
    }

    override fun onDeleteInteraction(item: Item) {
        presenter.onDeletaItem(this, item)
    }

    override fun onAddInteraction() {
        val fragmentAddItem = AdicionaItemFragment.newInstance(null)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fmMaster, fragmentAddItem)
            .addToBackStack(null)
            .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ADICIONA_ITEM && resultCode == Activity.RESULT_OK) {
            val item: Item? = data?.getSerializableExtra(NOVO_ITEM) as Item
            if(item!=null) {
                if(index >= 0) {
                    todoList.set(index, item)
                    index = -1
                } else {
                    todoList.add(item)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onAtualizaLista(this)
    }
}
