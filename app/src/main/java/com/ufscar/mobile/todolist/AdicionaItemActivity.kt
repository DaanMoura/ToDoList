package com.ufscar.mobile.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_adiciona_item.*

class AdicionaItemActivity : AppCompatActivity() {
    private val NOVO_ITEM = "NovoItem"
    private val ITEM = "Item"
    var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adiciona_item)

        val item = intent.getSerializableExtra(ITEM) as Item?
        edtItem.setText(item?.texto)

        btnSave.setOnClickListener {
            val salva = Intent(this, TodoListActivity::class.java)
            salva.putExtra(NOVO_ITEM, edtItem.text.toString())
            setResult(Activity.RESULT_OK, salva)
            finish()
        }

    }
}
