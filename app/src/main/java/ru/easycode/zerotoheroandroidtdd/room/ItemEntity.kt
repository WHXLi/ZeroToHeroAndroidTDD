package ru.easycode.zerotoheroandroidtdd.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "items_table"
)
data class ItemEntity(

    @PrimaryKey
    @ColumnInfo(
        name = "id"
    ) val id: Long,

    @ColumnInfo(
        name = "text"
    ) val text: String,
)