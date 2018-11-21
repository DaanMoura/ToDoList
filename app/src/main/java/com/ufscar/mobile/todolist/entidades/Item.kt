package com.ufscar.mobile.todolist.entidades

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var todo: String,
    var date: String = "Data"):Serializable