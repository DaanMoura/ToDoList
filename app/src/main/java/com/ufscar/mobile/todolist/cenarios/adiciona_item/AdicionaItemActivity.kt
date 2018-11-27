package com.ufscar.mobile.todolist.cenarios.adiciona_item

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.ufscar.mobile.todolist.entidades.Item
import com.ufscar.mobile.todolist.R
import kotlinx.android.synthetic.main.activity_adiciona_item.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class AdicionaItemActivity : AppCompatActivity(), AdicionaItemContract.View {
    private val ITEM = "Item"
    var item: Item? = null

    val presenter: AdicionaItemContract.Presenter =
        AdicionaItemPresenter(this)

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
