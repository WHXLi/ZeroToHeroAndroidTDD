package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.livedata.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.navigation.screen.AddScreen
import ru.easycode.zerotoheroandroidtdd.navigation.screen.MainScreen
import ru.easycode.zerotoheroandroidtdd.repository.Repository

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val navigation: Navigation.Mutable,
): ViewModel(), ListLiveDataWrapper.Read {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun liveData(): LiveData<List<String>> = liveDataWrapper.liveData()

    fun init() {
        viewModelScope.launch(dispatcher) {
            val value = repository.list()
            liveDataWrapper.update(value)
        }
    }

    fun navigateToAdd() {
        navigation.update(AddScreen)
    }
}