package ru.easycode.zerotoheroandroidtdd.state

import java.io.Serializable

sealed interface TextViewState : Serializable {
    fun changeText(text: String): TextViewState
    fun hide(): TextViewState
    fun remove(): TextViewState
    fun show(): TextViewState

    object Visible : TextViewState {
        private fun readResolve(): Any = Visible
        override fun changeText(text: String) = Changed(text)
        override fun hide() = Hidden
        override fun remove() = Removed
        override fun show() = this
    }

    object Hidden : TextViewState {
        private fun readResolve(): Any = Hidden
        override fun changeText(text: String) = Changed(text)
        override fun hide() = this
        override fun remove() = Removed
        override fun show() = Visible
    }

    object Removed : TextViewState {
        private fun readResolve(): Any = Removed
        override fun changeText(text: String) = this
        override fun hide() = this
        override fun remove() = this
        override fun show() = this
    }

    data class Changed(val text: String) : TextViewState {
        override fun changeText(text: String) = Changed(text)
        override fun hide() = Hidden
        override fun remove() = Removed
        override fun show() = Visible
    }
}