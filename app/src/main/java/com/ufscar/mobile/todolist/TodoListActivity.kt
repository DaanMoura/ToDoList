package com.ufscar.mobile.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_adiciona_item.*
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.todo_item.*

class TodoListActivity : AppCompatActivity() {
    private val ADICIONA_ITEM = 1
    private val NOVO_ITEM = "NovoItem"
    private val ITEM = "Item"
    var index: Int = -1

    var todoList = ArrayList<Item>()
    val adapter = ItemAdapter(todoList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        btnAdd.setOnClickListener {
            val adiciona = Intent(this, AdicionaItemActivity::class.java)
            startActivityForResult(adiciona,ADICIONA_ITEM)
        }

        adapter.configuraCliqueBotao { index ->
            todoList.removeAt(index)
            carregaLista()
        }
    }

    private fun carregaLista() {
        val layoutManager = LinearLayoutManager(this)
        adapter.configuraClique {item, index ->
            this.index = index
            val editaItem = Intent(this, AdicionaItemActivity::class.java)
            editaItem.putExtra(ITEM, todoList.get(index))
            startActivityForResult(editaItem, ADICIONA_ITEM)
        }

        rvItem.adapter = adapter
        rvItem.layoutManager = layoutManager
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ADICIONA_ITEM && resultCode == Activity.RESULT_OK) {
            val item: String? = data?.getStringExtra(NOVO_ITEM)
            if(item!=null) {
                if(index >= 0) {
                    todoList[index].texto = item
                    index = -1
                } else {
                    todoList.add(Item(item))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        carregaLista()
    }
}
