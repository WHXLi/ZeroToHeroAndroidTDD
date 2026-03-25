package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelFactory
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelProvider

class App: Application(), ViewModelProvider.Create {
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()
        viewModelFactory = ViewModelFactory.Base(ViewModelProvider.Base())
    }

    override fun <T : ViewModel> createViewModel(viewModelClass: Class<T>): T {
        return viewModelFactory.createViewModel(viewModelClass)
    }
}