package com.ufscar.mobile.todolist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.todo_item.view.*

class ItemAdapter(val todoList: List<String>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var clique: ((item:String, index: Int) -> Unit)? = null
    var cliqueBotao: ((index: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: String = todoList[position]
        holder.bindView(item, clique, cliqueBotao)
    }

    fun configuraClique(clique: ((item:String, index: Int) -> Unit)) {
        this.clique = clique
    }

    fun configuraCliqueBotao(cliqueBotao: ((index: Int) -> Unit)) {
        this.cliqueBotao = cliqueBotao
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        fun bindView(item: String,
                     clique: ((item:String, index: Int) -> Unit)?,
                     cliqueBotao: ((index: Int) -> Unit)?) {
            itemView.txtItem.text = item

            if(clique != null) {
                itemView.setOnClickListener {
                    clique.invoke(item, adapterPosition)
                }
            }

            if(cliqueBotao != null) {
                itemView.btnDone.setOnClickListener {
                    cliqueBotao.invoke(adapterPosition)
                }
            }
        }

    }
}
