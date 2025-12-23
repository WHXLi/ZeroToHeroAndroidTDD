package ru.easycode.zerotoheroandroidtdd.repository

import ru.easycode.zerotoheroandroidtdd.state.UiState
import ru.easycode.zerotoheroandroidtdd.wrapper.LiveDataWrapper

sealed class LoadResult {

    abstract fun show(updateLiveData: LiveDataWrapper.Update)

    data class Success(val data: SimpleResponse): LoadResult() {

        override fun show(updateLiveData: LiveDataWrapper.Update) {
            data.text?.let { updateLiveData.update(UiState.ShowData(it)) }
        }
    }

    data class Error(val noConnection: Boolean): LoadResult() {

        override fun show(updateLiveData: LiveDataWrapper.Update) {
            val errorText = if (noConnection) "No internet connection" else "Something went wrong"
            updateLiveData.update(UiState.ShowData(errorText))
        }
    }
}