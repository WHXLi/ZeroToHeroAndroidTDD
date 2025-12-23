package ru.easycode.zerotoheroandroidtdd.state

import java.io.Serializable

sealed class UiState: Serializable {

    abstract fun apply(handler: Handler)

    object ShowProgress: UiState() {

        private fun readResolve(): Any = ShowProgress

        override fun apply(handler: Handler) {
            handler.showProgress()
        }
    }

    data class ShowData(val text: String): UiState() {

        override fun apply(handler: Handler) {
            handler.showData(text)
        }
    }

    interface Handler {

        fun showProgress()

        fun showData(text: String)
    }
}