package ru.easycode.zerotoheroandroidtdd.feature.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.livedata.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ClearViewModelHandler
import ru.easycode.zerotoheroandroidtdd.repository.Repository

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val clear: ClearViewModelHandler,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun add(value: String) {
        viewModelScope.launch(dispatcher) {
            repository.add(value)
            liveDataWrapper.add(value)
            comeback()
        }
    }

    fun comeback() {
        clear.clearViewModel(AddViewModel::class.java)
    }
}