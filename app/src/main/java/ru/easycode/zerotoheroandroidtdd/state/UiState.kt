package ru.easycode.zerotoheroandroidtdd.state

import java.io.Serializable

sealed class UiState : Serializable {

    abstract fun apply(handler: Handler)

    object ShowProgress : UiState() {

        override fun apply(handler: Handler) = handler.showProgress()

        private fun readResolve(): Any = ShowProgress
    }

    data class ShowData(
        private val text: String
    ) : UiState() {

        override fun apply(handler: Handler) = handler.showData(text)
    }

    interface Handler {

        fun showProgress()

        fun showData(text: String)
    }
}