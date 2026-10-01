package ru.easycode.zerotoheroandroidtdd.navigation.screen

import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.feature.list.AddFragment

object AddScreen: Screen {

    override fun show(
        supportFragmentManager: FragmentManager,
        containerId: Int
    ) {
        AddFragment().show(supportFragmentManager, AddFragment::class.java.name)
    }
}