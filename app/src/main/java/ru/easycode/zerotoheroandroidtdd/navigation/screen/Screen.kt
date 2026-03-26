package ru.easycode.zerotoheroandroidtdd.navigation.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {
    fun show(supportFragmentManager: FragmentManager, containerId: Int)

    abstract class Replace(private val fragmentClass: Class<out Fragment>): Screen {
        override fun show(
            supportFragmentManager: FragmentManager,
            containerId: Int,
        ) {
            val fragment = fragmentClass.getDeclaredConstructor().newInstance()
            supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment)
                .commit()
        }
    }

    abstract class Add(private val fragmentClass: Class<out Fragment>): Screen {
        override fun show(
            supportFragmentManager: FragmentManager,
            containerId: Int,
        ) {
            val fragment = fragmentClass.getDeclaredConstructor().newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(containerId, fragment)
                .addToBackStack(fragmentClass.name)
                .commit()
        }
    }

    object Pop: Screen {
        override fun show(
            supportFragmentManager: FragmentManager,
            containerId: Int
        ) {
            supportFragmentManager.popBackStack()
        }
    }
}