package ru.easycode.zerotoheroandroidtdd.feature.clear

import androidx.lifecycle.ViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out ViewModel>)
}