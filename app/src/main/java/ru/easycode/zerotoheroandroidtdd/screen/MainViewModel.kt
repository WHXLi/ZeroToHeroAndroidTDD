package ru.easycode.zerotoheroandroidtdd.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.wrapper.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.wrapper.ListLiveDataWrapper

class MainViewModel(
    private val charSequenceLiveDataWrapper: ListLiveDataWrapper.CharSequenceList,
): ViewModel() {

    fun add(text: String) {
        charSequenceLiveDataWrapper.add(text)
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        bundleWrapper.save(charSequenceLiveDataWrapper.list)
    }

    class Factory(): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val liveDataWrapper = ListLiveDataWrapper.Base()
            return MainViewModel(liveDataWrapper) as T
        }
    }
}