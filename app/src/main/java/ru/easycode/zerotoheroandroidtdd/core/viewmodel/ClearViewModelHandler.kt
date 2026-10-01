package ru.easycode.zerotoheroandroidtdd.core.viewmodel

import androidx.lifecycle.ViewModel

interface ClearViewModelHandler {

    fun clearViewModel(clasz: Class<out ViewModel>)
}