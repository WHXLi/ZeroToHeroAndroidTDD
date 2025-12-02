package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    val liveDataWrapper: LiveDataWrapper,
    val repository: Repository,
): ViewModel() {

    fun stateLiveData() = liveDataWrapper.liveData()

    fun load() = viewModelScope.launch {
        liveDataWrapper.update(UiState.ShowProgress)
        repository.load()
        liveDataWrapper.update(UiState.ShowData)
    }
}