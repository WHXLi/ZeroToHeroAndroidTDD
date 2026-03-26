package ru.easycode.zerotoheroandroidtdd.core.viewmodel

import androidx.lifecycle.ViewModel

interface ViewModelFactory: ViewModelProvider.Provide {
    class Base(
        private val viewModelProvider: ViewModelProvider.Create,
    ): ViewModelFactory {
        private val cachedViewModels: MutableMap<Class<out ViewModel>, ViewModel> = mutableMapOf()

        override fun <T : ViewModel> createViewModel(viewModelClass: Class<T>): T {
            val viewModel = cachedViewModels[viewModelClass]
            return if (viewModel == null) viewModelProvider.createViewModel(viewModelClass).also {
                cachedViewModels[viewModelClass] = it
            } else viewModel as T
        }

        override fun removeViewModel(viewModelClass: Class<out ViewModel>) {
            cachedViewModels.remove(viewModelClass)
        }
    }
}