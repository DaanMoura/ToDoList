package com.ufscar.mobile.todolist

import android.arch.persistence.room.*

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