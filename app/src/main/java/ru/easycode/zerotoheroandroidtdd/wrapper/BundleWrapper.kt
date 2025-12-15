package ru.easycode.zerotoheroandroidtdd.wrapper

import android.os.Build
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.state.UiState

private const val BUNDLE_KEY = "BUNDLE_KEY"

interface BundleWrapper {

    interface Save {

        fun save(uiState: UiState)
    }

    interface Restore {

        fun restore(): UiState
    }

    interface Mutable: Save, Restore

    class Base(
        private val bundle: Bundle,
    ): Mutable {

        override fun save(uiState: UiState) {
            bundle.putSerializable(BUNDLE_KEY, uiState)
        }

        override fun restore(): UiState {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(BUNDLE_KEY, UiState::class.java) as UiState
            } else bundle.getSerializable(BUNDLE_KEY) as UiState
        }
    }
}