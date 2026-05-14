package ru.easycode.zerotoheroandroidtdd.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.easycode.zerotoheroandroidtdd.database.dao.ItemsDao
import ru.easycode.zerotoheroandroidtdd.database.entity.ItemCache

@Database(
    entities = [ItemCache::class],
    version = 1,
)
abstract class ItemsDataBase: RoomDatabase() {

    abstract fun itemsDao(): ItemsDao
}