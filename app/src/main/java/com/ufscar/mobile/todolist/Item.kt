package com.ufscar.mobile.todolist

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class Item (
    var texto: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0 ):Serializable