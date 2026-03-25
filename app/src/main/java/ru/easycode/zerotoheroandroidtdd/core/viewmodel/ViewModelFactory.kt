package ru.easycode.zerotoheroandroidtdd.core.viewmodel

import androidx.lifecycle.ViewModel

interface ViewModelFactory: ViewModelProvider.Provide {
    class Base(
        private val viewModelProvider: ViewModelProvider.Create,
    ): ViewModelFactory {
        private val map: MutableMap<Class<out ViewModel>, ViewModel> = mutableMapOf()

        override fun <T : ViewModel> createViewModel(viewModelClass: Class<T>): T {
            return if (map.containsKey(viewModelClass))
                map[viewModelClass] as T
            else {
                val viewModel = viewModelProvider.createViewModel(viewModelClass)
                map[viewModelClass] = viewModel
                viewModel
            }
        }

        override fun removeViewModel(viewModelClass: Class<out ViewModel>) {
            map.remove(viewModelClass)
        }
    }
}