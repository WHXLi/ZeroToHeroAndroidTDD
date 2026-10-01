package ru.easycode.zerotoheroandroidtdd.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemsDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    ) fun add(item: ItemEntity)

    @Query(
        "SELECT * FROM items_table"
    ) fun list(): List<ItemEntity>
}