package ru.easycode.zerotoheroandroidtdd.feature.create

import ru.easycode.zerotoheroandroidtdd.feature.clear.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.feature.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.navigation.Navigation

class CreateViewModel(
    addLiveDataWrapper: ListLiveDataWrapper.Add,
    navigation: Navigation.Update,
    clearViewModel: ClearViewModel,
): ListLiveDataWrapper.Add {

    override fun add(source: CharSequence) {
        TODO("Not yet implemented")
    }
}