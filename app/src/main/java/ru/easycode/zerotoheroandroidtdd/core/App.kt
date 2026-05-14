package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App: Application(), ProvideViewModel {

    private lateinit var factory: ProvideViewModel
    private val store = HashMap<Class<out ViewModel>, ViewModel?>()
    private val clear = initClear()

    override fun onCreate() {
        super.onCreate()
        val core = Core(this)
        factory = ProvideViewModel.Base(core, clear)
    }

    override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
        if (store[clasz] == null) store[clasz] = factory.viewModel(clasz)
        return store[clasz] as T
    }

    private fun initClear() = object : ClearViewModel {
        override fun clearViewModel(clasz: Class<out ViewModel>) {
            store[clasz] = null
        }
    }
}



