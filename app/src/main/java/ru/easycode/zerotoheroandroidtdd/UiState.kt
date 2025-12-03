package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

sealed class UiState: Serializable {

    abstract fun apply(handler: Handler)

    object ShowProgress: UiState() {
        override fun apply(handler: Handler) {
            handler.showProgressBar()
        }
    }

    object ShowData: UiState() {
        override fun apply(handler: Handler) {
            handler.hideProgressBar()
            handler.showData()
        }
    }

    sealed class Handler {

        abstract fun showProgressBar()

        abstract fun hideProgressBar()

        abstract fun showData()
    }
}