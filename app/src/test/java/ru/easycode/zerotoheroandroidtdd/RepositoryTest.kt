package ru.easycode.zerotoheroandroidtdd

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.repository.Now
import ru.easycode.zerotoheroandroidtdd.repository.Repository
import ru.easycode.zerotoheroandroidtdd.room.ItemEntity
import ru.easycode.zerotoheroandroidtdd.room.ItemsDao

class RepositoryTest {

    @Test
    fun test() {
        val now = FakeNow.Base(7777L)
        val dataSource = FakeDataSource.Base()
        val repository: Repository.Mutable = Repository.Base(dataSource = dataSource, now = now)

        dataSource.expectList(
            listOf(
                ItemEntity(id = 0L, text = "first"),
                ItemEntity(id = 1L, text = "second")
            )
        )

        val actual: List<String> = repository.list()
        val expected = listOf("first", "second")
        assertEquals(expected, actual)

        repository.add(value = "third")
        dataSource.checkList(
            listOf(
                ItemEntity(id = 0L, text = "first"),
                ItemEntity(id = 1L, text = "second"),
                ItemEntity(id = 7778L, text = "third")
            )
        )
    }
}

private interface FakeNow : Now {

    class Base(private var value: Long) : FakeNow {

        override fun nowMillis(): Long {
            return ++value
        }
    }
}

private interface FakeDataSource : ItemsDao {

    fun checkList(expected: List<ItemEntity>)

    fun expectList(list: List<ItemEntity>)

    class Base : FakeDataSource {

        private val list = mutableListOf<ItemEntity>()

        override fun checkList(expected: List<ItemEntity>) {
            assertEquals(expected, list)
        }

        override fun expectList(list: List<ItemEntity>) {
            this.list.addAll(list)
        }

        override fun list(): List<ItemEntity> {
            return list
        }

        override fun add(item: ItemEntity) {
            list.add(item)
        }
    }
}