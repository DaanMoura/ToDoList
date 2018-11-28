package com.ufscar.mobile.todolist.cenarios.todo_list


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ufscar.mobile.todolist.R
import com.ufscar.mobile.todolist.cenarios.adiciona_item.AdicionaItemFragment
import com.ufscar.mobile.todolist.entidades.Item
import kotlinx.android.synthetic.main.fragment_todo_list.*
import java.lang.NullPointerException
import java.lang.RuntimeException
import java.util.ArrayList

class TodoListFragment : Fragment() {
//
//    val presenter: TodoListContract.Presenter =
//        TodoListPresenter(activity as TodoListContract.View)

    companion object {
        private val ARG_LIST =  "arg_list"
        fun newInstance(list: ArrayList<Item>) =
            TodoListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_LIST, list)
                }
        }
    }

    var listener: onFragmentInteractionListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todoList = getTodoList()
        val adapter = ItemAdapter(todoList)


        activity?.let{ that ->
            adapter.configuraClique {index ->
                listener?.onItemInteraction(todoList.get(index))
//                val editaItem = Intent(activity, AdicionaItemActivity::class.java)
//                editaItem.putExtra(ITEM, todoList.get(index))
//                startActivityForResult(editaItem, ADICIONA_ITEM)
            }
            val layoutManager = LinearLayoutManager(that)
            rvItem.adapter = adapter
            rvItem.layoutManager = layoutManager
        }

        adapter.configuraCliqueBotao { index ->
            listener?.onDeleteInteraction(todoList.get(index))
        }

        btnAdd.setOnClickListener {
            listener?.onAddInteraction()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is TodoListFragment.onFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException (
                context.toString() + "must implement TodoListFragment.onFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun getTodoList(): ArrayList<Item> {
        val todoList = arguments?.getSerializable(ARG_LIST) as ArrayList<Item>
        if(todoList == null) {
            throw NullPointerException("Todo List can not be null")
        }

        return todoList
    }

    interface onFragmentInteractionListener {
        fun onItemInteraction(item: Item)
        fun onDeleteInteraction(item: Item)
        fun onAddInteraction()
    }


}
