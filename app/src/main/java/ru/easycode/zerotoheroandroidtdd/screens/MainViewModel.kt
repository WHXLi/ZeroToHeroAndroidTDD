package ru.easycode.zerotoheroandroidtdd.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.wrappers.ListLiveDataWrapper

class MainViewModel(
    private val listLiveDataWrapper: ListLiveDataWrapper
): ViewModel() {

    fun getLiveData() = listLiveDataWrapper.liveData()

    fun add(text: String) {
        listLiveDataWrapper.add(text)
    }

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        listLiveDataWrapper.update(bundle.restore())
    }

    class Factory(): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(
            listLiveDataWrapper = ListLiveDataWrapper.Base()
        ) as T
    }
}