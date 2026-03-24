package ru.easycode.zerotoheroandroidtdd.core.viewmodel

import androidx.lifecycle.ViewModel

interface ViewModelProvider {
    interface Create {
        fun <T : ViewModel> create(viewModelClass: Class<T>): T
    }

    interface Remove {
        fun remove(viewModelClass: Class<out ViewModel>)
    }

    interface Provide: Create, Remove
}