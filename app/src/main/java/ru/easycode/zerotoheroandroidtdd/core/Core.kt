package ru.easycode.zerotoheroandroidtdd.core

import android.content.Context
import androidx.room.Room
import ru.easycode.zerotoheroandroidtdd.database.ItemsDataBase

class Core(private val context: Context) {

    private val dataBase by lazy {
        Room.databaseBuilder(
            context,
            ItemsDataBase::class.java,
            "items_database"
        ).build()
    }

    fun dao() = dataBase.itemsDao()
}