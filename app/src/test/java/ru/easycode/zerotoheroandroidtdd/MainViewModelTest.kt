package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.screen.MainViewModel
import ru.easycode.zerotoheroandroidtdd.wrapper.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.wrapper.ListLiveDataWrapper

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var listLiveDataWrapper: FakeListLiveDataWrapper

    @Before
    fun init() {
        listLiveDataWrapper = FakeListLiveDataWrapper.Base()
        viewModel = MainViewModel(charSequenceLiveDataWrapper = listLiveDataWrapper)
    }

    @Test
    fun test() {
        viewModel.add(text = "first")
        listLiveDataWrapper.checkListSame(listOf("first"))

        viewModel.add(text = "second")
        listLiveDataWrapper.checkListSame(listOf("first", "second"))

        val bundleWrapper: BundleWrapper.Mutable = FakeBundleWrapper()
        val bundleWrapperSave: BundleWrapper.Save = bundleWrapper
        val bundleWrapperRestore: BundleWrapper.Restore = bundleWrapper

        viewModel.save(bundle = bundleWrapperSave)

        init()

        viewModel.restore(bundle = bundleWrapperRestore)
        listLiveDataWrapper.checkListSame(listOf("first", "second"))
    }
}

private interface FakeListLiveDataWrapper : ListLiveDataWrapper {

    fun checkListSame(expected: List<CharSequence>)

    class Base : FakeListLiveDataWrapper {

        override val mutableLiveData: MutableLiveData<List<CharSequence>>
            get() = TODO("Not yet implemented")
        override val list = ArrayList<CharSequence>()

        override fun checkListSame(expected: List<CharSequence>) {
            assertEquals(expected, list)
        }

        override fun liveData(): LiveData<List<CharSequence>> {
            throw IllegalStateException("not used here")
        }

        override fun add(new: CharSequence) {
            list.add(new)
        }

        override fun save(bundle: BundleWrapper.Save) {
            bundle.save(list)
        }

        override fun update(param: List<CharSequence>) {
            this.list.addAll(param)
        }
    }
}

class FakeBundleWrapper : BundleWrapper.Mutable {

    private val cache = ArrayList<CharSequence>()

    override fun save(list: ArrayList<CharSequence>) {
        cache.addAll(list)
    }

    override fun restore(): List<CharSequence> {
        return cache
    }
}