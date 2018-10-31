package com.ufscar.mobile.todolist

import android.app.Activity
import android.content.Intent
import android.icu.text.DateTimePatternGenerator
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_adiciona_item.*
import kotlinx.android.synthetic.main.todo_item.*
import org.jetbrains.anko.activityUiThreadWithContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Error
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class AdicionaItemActivity : AppCompatActivity() {
    private val NOVO_ITEM = "NovoItem"
    private val ITEM = "Item"
    var item: Item? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adiciona_item)

        item = intent.getSerializableExtra(ITEM) as Item?
        edtItem.setText(item?.texto)

        btnSave.setOnClickListener {

            val current = LocalDateTime.now().toString()
//            Toast.makeText(this, "$current", Toast.LENGTH_SHORT).show()
//            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
//            val formatted = current.format(formatter)


//            val salva = Intent(this, TodoListActivity::class.java)
            if(item == null) {
                item = Item(edtItem.text.toString(), current)
            } else {
                item?.texto = edtItem.text.toString()
                item?.createdAt = current
            }
//            salva.putExtra(NOVO_ITEM, item)
//            setResult(Activity.RESULT_OK, salva)

            val itemDao: ItemDAO = AppDatabase.getInstance(this).itemDao()

            Toast.makeText(this, "$current", Toast.LENGTH_SHORT).show()
            doAsync {
                try {
                    itemDao.insert(item!!)
                }catch (err: Error){
                    val x = err
                }
                uiThread {
                    finish()
                }
            }
        }

    }
}
