package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.features.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.features.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.features.time.Now
import ru.easycode.zerotoheroandroidtdd.repository.Repository

interface ProvideViewModel {

    fun <T: ViewModel> viewModel(clasz: Class<T>): T



    class Base(core: Core, private val clear: ClearViewModel): ProvideViewModel {

        private val repository = Repository.Base(core.dao(), Now.Base())
        private val liveDataWrapper = ListLiveDataWrapper.Base()

        override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
            return (if (clasz == MainViewModel::class.java)
                MainViewModel(repository, liveDataWrapper)
            else AddViewModel(repository, liveDataWrapper, clear)) as T
        }
    }
}