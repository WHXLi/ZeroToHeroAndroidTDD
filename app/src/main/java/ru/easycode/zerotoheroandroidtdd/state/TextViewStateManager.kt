import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.state.TextViewState
import java.io.Serializable

class TextViewStateManager(
    private var textView: TextView,
    private var removeButton: Button? = null,
    private var hideButton: Button? = null,
) : Serializable {

    var state: TextViewState = TextViewState.Visible
        private set

    fun restore(
        textView: TextView,
        removeButton: Button? = null,
        hideButton: Button? = null,
    ) {
        this.textView = textView
        this.removeButton = removeButton
        this.hideButton = hideButton
        applyCurrentState()
    }

    fun changeText(text: String) {
        state = state.changeText(text)
        applyCurrentState()
    }

    fun hideView() {
        state = state.hide()
        applyCurrentState()
    }

    fun removeView() {
        state = state.remove()
        applyCurrentState()
    }

    fun show() {
        state = state.show()
        applyCurrentState()
    }

    private fun applyCurrentState() = when (val currentState = state) {
        is TextViewState.Changed -> {
            textView.text = currentState.text
            textView.visibility = View.VISIBLE
            enableButtons()
        }
        is TextViewState.Visible-> {
            textView.visibility = View.VISIBLE
            enableButtons()
        }
        is TextViewState.Hidden -> {
            textView.visibility = View.GONE
            enableButtons()
        }
        is TextViewState.Removed -> {
            textView.post {
                textView.parent?.let {
                    (it as? ViewGroup)?.removeView(textView)
                }
            }
            disableButtons()
        }
    }

    private fun enableButtons() {
        removeButton?.isEnabled = true
        hideButton?.isEnabled = true
    }

    private fun disableButtons() {
        removeButton?.isEnabled = false
        hideButton?.isEnabled = false
    }
}
