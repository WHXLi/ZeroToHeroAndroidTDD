package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.Core
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelFactory
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.navigation.Navigation

class App: Application(), ViewModelProvider.Provide {

    private lateinit var factory: ViewModelFactory
    val navigation = Navigation.Abstract()

    override fun onCreate() {
        super.onCreate()
        factory = ViewModelFactory.Base(
            viewModelProvider = ViewModelProvider.Base(
                core = Core(this),
                navigation = navigation,
            )
        )
    }

    override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
        return factory.create(viewModelClass)
    }

    override fun <T : ViewModel> remove(viewModelClass: Class<out ViewModel>) {
        factory.remove<ViewModel>(viewModelClass)
    }
}