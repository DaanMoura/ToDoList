package com.ufscar.mobile.todolist.cenarios.adiciona_item

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ufscar.mobile.todolist.R
import com.ufscar.mobile.todolist.cenarios.todo_list.TodoListActivity
import com.ufscar.mobile.todolist.cenarios.todo_list.TodoListFragment
import com.ufscar.mobile.todolist.entidades.Item
import kotlinx.android.synthetic.main.fragment_adiciona_item.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.newTask
import java.lang.NullPointerException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.ArrayList


class AdicionaItemFragment : Fragment(),  AdicionaItemContract.View {
    private val ARG_ITEM = "arg_item"

    companion object {
        fun newInstance(item: Item?) =
            AdicionaItemFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ITEM, item)
                }
            }
    }
    val presenter: AdicionaItemContract.Presenter =
        AdicionaItemPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adiciona_item, container, false)
    }

    @TargetApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var item = getItem()
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
                activity?.let {that ->
                    presenter.onSalvaItem(that, item)
                }
            }
        }
    }

    fun getItem(): Item? {
        val item = arguments?.getSerializable(ARG_ITEM) as Item?
        return item
    }

    override fun salvoComSucesso() {
        activity?.let {that ->
            val intent = Intent(that, TodoListActivity::class.java)
            intent.newTask().clearTask()
            startActivity(intent)
        }
    }

}
