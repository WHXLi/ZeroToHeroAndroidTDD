package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.count.Count
import ru.easycode.zerotoheroandroidtdd.state.UiState

class MainViewModel(): ViewModel() {

    private lateinit var count: Count
    private val uiState = MutableLiveData<UiState>()

    fun getUiState(): LiveData<UiState> = uiState

    fun initial(number: String, count: Count) {
        this.count = count
        uiState.postValue(count.initial(number))
    }

    fun increment(number: String) {
        uiState.postValue(count.increment(number))
    }

    fun decrement(number: String) {
        uiState.postValue(count.decrement(number))
    }

    fun getCount(): Count {
        val default = Count.Base(2,4,0)
        return if (::count.isInitialized) count else default
    }
}