package ru.easycode.zerotoheroandroidtdd.wrapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.state.UiState

interface LiveDataWrapper {

    fun liveData(): LiveData<UiState>

    fun save(bundleWrapper: BundleWrapper.Save)

    fun update(value: UiState)

    class Base(
        private val liveData: MutableLiveData<UiState> = SingleLiveEvent(),
    ): LiveDataWrapper {

        override fun liveData(): LiveData<UiState> = liveData

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: UiState) {
            liveData.value = value
        }

    }
}