package ru.easycode.zerotoheroandroidtdd.repository

import ru.easycode.zerotoheroandroidtdd.room.ItemEntity
import ru.easycode.zerotoheroandroidtdd.room.ItemsDao

interface Repository {

    interface Add {

        fun add(value: String)
    }

    interface Read {

        fun list(): List<String>
    }

    interface Mutable: Read, Add

    class Base(
        private val dataSource: ItemsDao,
        private val now: Now
    ) : Mutable, Read {

        override fun add(value: String) {
            dataSource.add(
                ItemEntity(
                    id = now.nowMillis(),
                    text = value,
                )
            )
        }

        override fun list(): List<String> {
            return dataSource.list().map { it.text }
        }
    }
}