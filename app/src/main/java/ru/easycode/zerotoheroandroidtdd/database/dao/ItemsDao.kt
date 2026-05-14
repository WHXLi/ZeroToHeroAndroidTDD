package ru.easycode.zerotoheroandroidtdd.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.easycode.zerotoheroandroidtdd.database.entity.ItemCache

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items_table")
    fun list(): List<ItemCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item: ItemCache)
}