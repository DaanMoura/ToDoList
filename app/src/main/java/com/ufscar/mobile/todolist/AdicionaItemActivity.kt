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

class AdicionaItemActivity : AppCompatActivity(), AdicionaItemContract.View {
    private val ITEM = "Item"
    var item: Item? = null

    val presenter: AdicionaItemContract.Presenter = AdicionaItemPresenter(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adiciona_item)

        item = intent.getSerializableExtra(ITEM) as Item?
        edtItem.setText(item?.texto)

        btnSave.setOnClickListener {

            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            val formatted = current.format(formatter)

            if(item == null) {
                item = Item(edtItem.text.toString(), formatted)
            } else {
                item?.texto = edtItem.text.toString()
                item?.createdAt = formatted
            }

            item?.let {item ->
                presenter.onSalvaItem(this, item)
            }
        }

    }

    override fun salvoComSucesso() {
        finish()
    }
}
