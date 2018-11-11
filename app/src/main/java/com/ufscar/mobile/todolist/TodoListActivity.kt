package com.ufscar.mobile.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_todo_list.*
import org.jetbrains.anko.activityUiThreadWithContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TodoListActivity : AppCompatActivity(), TodoListContract.View {
    private val ADICIONA_ITEM = 1
    private val NOVO_ITEM = "NovoItem"
    private val ITEM = "Item"
    var index: Int = -1

    var todoList = ArrayList<Item>()
    val presenter: TodoListContract.Presenter = TodoListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        btnAdd.setOnClickListener {
            val adiciona = Intent(this, AdicionaItemActivity::class.java)
            startActivity(adiciona)
        }

    }

    override fun exibeLista(list: ArrayList<Item>) {

        todoList = list

        val adapter = ItemAdapter(todoList)

        adapter.configuraClique {index ->
            val editaItem = Intent(this, AdicionaItemActivity::class.java)
            editaItem.putExtra(ITEM, todoList.get(index))
            startActivityForResult(editaItem, ADICIONA_ITEM)
        }

        adapter.configuraCliqueBotao { index ->
            presenter.onDeletaItem(this, todoList.get(index))
        }
        val layoutManager = LinearLayoutManager(this)

        rvItem.adapter = adapter
        rvItem.layoutManager = layoutManager

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
