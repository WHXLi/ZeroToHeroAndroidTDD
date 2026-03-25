package ru.easycode.zerotoheroandroidtdd.core.viewmodel

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.feature.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.feature.list.ListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.navigation.Navigation

interface ViewModelProvider {
    interface Create {
        fun <T : ViewModel> createViewModel(viewModelClass: Class<T>): T
    }

    interface Remove {
        fun removeViewModel(viewModelClass: Class<out ViewModel>)
    }

    interface Provide: Create, Remove

    class Base: ViewModelProvider.Create {
        private val navigation = Navigation.Base()

        override fun <T : ViewModel> createViewModel(viewModelClass: Class<T>): T = when(viewModelClass) {
            MainViewModel::class.java -> MainViewModel(navigation)
            ListViewModel::class.java -> ListViewModel(
                liveDataWrapper = ListLiveDataWrapper.Base(),
                navigation = navigation,
            )
            else -> throw IllegalStateException("unknow viewModelClass: $viewModelClass")
        } as T
    }
}