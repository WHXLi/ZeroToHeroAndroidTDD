package ru.easycode.zerotoheroandroidtdd.core.viewmodel

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.Core
import ru.easycode.zerotoheroandroidtdd.core.livedata.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.feature.list.AddViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.repository.Now
import ru.easycode.zerotoheroandroidtdd.repository.Repository

interface ViewModelProvider {

    interface Create {
        fun <T : ViewModel> create(viewModelClass: Class<T>): T
    }

    interface Remove {
        fun <T : ViewModel> remove(viewModelClass: Class<out ViewModel>)
    }

    interface Provide: Create, Remove

    class Base(
        core: Core,
        private val navigation: Navigation.Mutable
    ): Create {

        private val liveDataWrapper = ListLiveDataWrapper.Base()
        private val clearViewModelHandler: ClearViewModelHandler = initClearViewModelHandler()
        private val store = HashMap<Class<out ViewModel>, ViewModel?>()
        private val repository = Repository.Base(
            dataSource = core.dao(),
            now = Now.Base(),
        )

        override fun <T : ViewModel> create(viewModelClass: Class<T>): T = when (viewModelClass) {
            MainViewModel::class.java -> MainViewModel(
                repository = repository,
                liveDataWrapper = liveDataWrapper,
                navigation = navigation,
            )
            AddViewModel::class.java -> AddViewModel(
                repository = repository,
                liveDataWrapper = liveDataWrapper,
                clear = clearViewModelHandler,
            )
            else -> throw IllegalStateException("unknow viewModelClass: $viewModelClass")
        } as T

        private fun initClearViewModelHandler() = object : ClearViewModelHandler {

            override fun clearViewModel(clasz: Class<out ViewModel>) {
                store.clear()
            }
        }
    }
}