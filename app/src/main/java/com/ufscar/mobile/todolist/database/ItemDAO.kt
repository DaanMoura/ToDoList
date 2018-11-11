package com.ufscar.mobile.todolist.database

import android.arch.persistence.room.*
import com.ufscar.mobile.todolist.entidades.Item

@Dao
interface ItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    @Query("SELECT * FROM item")
    fun getAll(): List<Item>

    @Delete
    fun delete(item: Item)

    @Query("SELECT * FROM item WHERE id = :itemId LIMIT 1")
    fun getItem(itemId: Int): Item
}