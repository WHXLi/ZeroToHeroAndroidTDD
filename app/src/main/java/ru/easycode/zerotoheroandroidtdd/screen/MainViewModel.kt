package ru.easycode.zerotoheroandroidtdd.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.repository.Repository
import ru.easycode.zerotoheroandroidtdd.repository.SimpleService
import ru.easycode.zerotoheroandroidtdd.repository.TASK_URL
import ru.easycode.zerotoheroandroidtdd.state.UiState
import ru.easycode.zerotoheroandroidtdd.wrapper.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.wrapper.LiveDataWrapper

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper.Mutable,
    private val repository: Repository,
): ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun getUiStateLiveData() = liveDataWrapper.liveData()

    fun load() = viewModelScope.launch {
        liveDataWrapper.update(UiState.ShowProgress)
        repository.load().show(liveDataWrapper)
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }

    class Factory(
        private val service: SimpleService,
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val liveDataWrapper = LiveDataWrapper.Base()
            val repository = Repository.Base(service, TASK_URL)
            return MainViewModel(liveDataWrapper, repository) as T
        }
    }
}