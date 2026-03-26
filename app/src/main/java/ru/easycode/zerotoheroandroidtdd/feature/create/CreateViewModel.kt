package ru.easycode.zerotoheroandroidtdd.feature.create

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.feature.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.navigation.screen.Screen

class CreateViewModel(
    private val addLiveDataWrapper: ListLiveDataWrapper.Add,
    private val navigation: Navigation.Update,
    private val removeProvider: ViewModelProvider.Remove,
): ViewModel() {

    fun add(text: CharSequence) {
        addLiveDataWrapper.add(text)
        comeback()
    }

    fun comeback() {
        navigation.update(Screen.Pop)
        removeProvider.removeViewModel(this::class.java)
    }
}