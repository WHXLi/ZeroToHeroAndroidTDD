package ru.easycode.zerotoheroandroidtdd.navigation

import ru.easycode.zerotoheroandroidtdd.core.livedata.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.navigation.screen.Screen

interface Navigation {
    interface Read: LiveDataWrapper.Read<Screen>

    interface Update: LiveDataWrapper.Update<Screen>

    interface Mutable: Read, Update

    class Abstract : LiveDataWrapper.Abstract<Screen>(), Mutable
}