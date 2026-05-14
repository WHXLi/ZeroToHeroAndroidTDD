package ru.easycode.zerotoheroandroidtdd.features.main

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.BaseViewModel
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.repository.Repository

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
): BaseViewModel(), ListLiveDataWrapper.Read {

    override fun liveData(): LiveData<List<String>> = liveDataWrapper.liveData()

    fun init() {
        viewModelScope.launch(dispatcher) {
            liveDataWrapper.update(repository.list())
        }
    }
}