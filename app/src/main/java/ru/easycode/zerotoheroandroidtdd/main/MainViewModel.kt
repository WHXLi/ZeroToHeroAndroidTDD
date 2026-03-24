package ru.easycode.zerotoheroandroidtdd.main

import ru.easycode.zerotoheroandroidtdd.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.navigation.screen.ListScreen

class MainViewModel(
    val navigation: Navigation.Mutable,
) {
    fun init(firstRun: Boolean) {
        if (firstRun) navigation.update(ListScreen)
    }
}