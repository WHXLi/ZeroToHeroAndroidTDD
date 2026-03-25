package ru.easycode.zerotoheroandroidtdd.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.bundle.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.navigation.screen.CreateScreen

class ListViewModel(
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val navigation: Navigation.Update,
): ViewModel(), ListLiveDataWrapper.Read {
    override fun liveData(): LiveData<List<CharSequence>> = liveDataWrapper.liveData()

    fun create() {
        navigation.update(CreateScreen)
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }
}