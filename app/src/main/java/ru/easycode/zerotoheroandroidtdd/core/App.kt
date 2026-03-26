package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import android.view.View
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelFactory
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelProvider

class App: Application(), ViewModelProvider.Create {
    private lateinit var viewModelFactory: ViewModelFactory
    private  val viewModelRemover = object : ViewModelProvider.Remove {
        override fun removeViewModel(viewModelClass: Class<out ViewModel>) {
            viewModelFactory.removeViewModel(viewModelClass)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val viewModelProvider = ViewModelProvider.Base(viewModelRemover)
        viewModelFactory = ViewModelFactory.Base(viewModelProvider)
    }

    override fun <T : ViewModel> createViewModel(viewModelClass: Class<T>): T {
        return viewModelFactory.createViewModel(viewModelClass)
    }
}