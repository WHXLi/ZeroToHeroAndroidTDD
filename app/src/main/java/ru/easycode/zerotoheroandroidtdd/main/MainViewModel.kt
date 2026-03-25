package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.navigation.screen.ListScreen
import ru.easycode.zerotoheroandroidtdd.navigation.screen.Screen

class MainViewModel(
    val navigation: Navigation.Mutable,
): ViewModel(), Navigation.Read {
    override fun liveData(): LiveData<Screen> = navigation.liveData()

    fun init(firstRun: Boolean) {
        if (firstRun) navigation.update(ListScreen)
    }
}