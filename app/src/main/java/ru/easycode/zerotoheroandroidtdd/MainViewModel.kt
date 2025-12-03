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

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        val uiState = bundleWrapper.restore()
        liveDataWrapper.update(uiState)
    }
}