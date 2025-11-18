package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.count.Count
import ru.easycode.zerotoheroandroidtdd.state.UiState

class MainViewModel(): ViewModel() {

    private val uiState = MutableLiveData<UiState>()

    fun getUiState(): LiveData<UiState> = uiState

    fun increment(number: String, step: Int, max: Int) {
        uiState.postValue(Count.Base(step, max).increment(number))
    }
}