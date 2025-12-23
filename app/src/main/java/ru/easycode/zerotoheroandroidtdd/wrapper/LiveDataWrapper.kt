package ru.easycode.zerotoheroandroidtdd.wrapper

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.state.UiState
import ru.easycode.zerotoheroandroidtdd.utils.SingleLiveEvent

interface LiveDataWrapper {

    fun liveData(): LiveData<UiState>

    fun save(bundleWrapper: BundleWrapper.Save)

    interface Update: LiveDataWrapper {

        fun update(value: UiState)
    }

    interface Mutable: Update, LiveDataWrapper

    class Base: Mutable {

        private val mutableLiveData = SingleLiveEvent<UiState>()

        override fun liveData(): LiveData<UiState> = mutableLiveData

        override fun save(bundleWrapper: BundleWrapper.Save) {
            mutableLiveData.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: UiState) {
            mutableLiveData.value = value
        }
    }
}