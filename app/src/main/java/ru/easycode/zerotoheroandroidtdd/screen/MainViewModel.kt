package ru.easycode.zerotoheroandroidtdd.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.remote.Repository
import ru.easycode.zerotoheroandroidtdd.state.UiState
import ru.easycode.zerotoheroandroidtdd.wrapper.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.wrapper.LiveDataWrapper

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository,
): ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun uiStateLiveData() = liveDataWrapper.liveData()

    fun load() = viewModelScope.launch {
        liveDataWrapper.update(UiState.ShowProgress)
        repository.load().text?.let {
            liveDataWrapper.update(UiState.ShowData(it))
        }
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }
}