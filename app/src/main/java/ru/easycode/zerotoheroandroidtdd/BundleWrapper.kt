package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle

private const val KEY_STATE = "KEY_STATE"

sealed interface BundleWrapper {

    interface Save {

        fun save(uiState: UiState)
    }

    interface Restore {

        fun restore(): UiState
    }

    interface Mutable : Save, Restore

    class Base(private val bundle: Bundle): Mutable {

        override fun save(uiState: UiState) {
            bundle.putSerializable(KEY_STATE, uiState)
        }

        override fun restore() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getSerializable(KEY_STATE, UiState::class.java) as UiState
        } else bundle.getSerializable(KEY_STATE) as UiState
    }
}