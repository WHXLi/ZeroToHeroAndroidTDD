package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.io.Serializable

class TextViewStateManager(
    private val rootView: ViewGroup,
    private val textView: TextView,
    private val hideButton: Button? = null,
    private val removeButton: Button? = null,
): Serializable {
    private var state: State = State.Visible

    fun change(text: String) {
        state = State.Changed
        state.apply(this, text)
    }

    fun show() {
        state = State.Visible
        state.apply(this)
    }

    fun remove() {
        state = State.Removed
        state.apply(this)
    }

    internal fun showView() {
        textView.visibility = View.VISIBLE
    }

    internal fun hideView() {
        textView.visibility = View.GONE
    }

    internal fun removeView() {
        rootView.removeView(textView)
    }

    internal fun disableButton() {
        removeButton?.isEnabled = false
        hideButton?.isEnabled = false
    }

    internal fun enableButton() {
        removeButton?.isEnabled = true
        hideButton?.isEnabled = true
    }
}


sealed interface State {
    fun apply(manager: TextViewStateManager, text: String? = null)

    object Changed: State {
        override fun apply(manager: TextViewStateManager, text: String?) {
            text?.let { manager.change(it) }
        }
    }

    object Visible : State {
        override fun apply(manager: TextViewStateManager, text: String?) {
            manager.showView()
            manager.enableButton()
        }
    }

    object Hidden : State {
        override fun apply(manager: TextViewStateManager, text: String?) {
            manager.hideView()
            manager.disableButton()
        }
    }

    object Removed : State {
        override fun apply(manager: TextViewStateManager, text: String?) {
            manager.removeView()
            manager.disableButton()
        }
    }
}