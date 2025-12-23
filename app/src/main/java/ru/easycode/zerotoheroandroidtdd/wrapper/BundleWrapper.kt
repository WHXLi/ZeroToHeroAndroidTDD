package ru.easycode.zerotoheroandroidtdd.wrapper

import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.state.UiState

private const val UI_STATE_KEY = "UI_STATE_KEY"

interface BundleWrapper {

    interface Save {

        fun save(uiState: UiState)
    }

    interface Restore {

        fun restore(): UiState
    }

    interface Mutable: Save, Restore

    class Base(private val bundle: Bundle): Mutable {

        override fun save(uiState: UiState) {
            bundle.putSerializable(UI_STATE_KEY, uiState)
        }

        override fun restore(): UiState {
            return bundle.getSerializable(UI_STATE_KEY, UiState::class.java) as UiState
        }
    }
}