package ru.easycode.zerotoheroandroidtdd

import TextViewStateManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible

private const val TEXT_VIEW_STATE_MANAGER = "TEXT_VIEW_STATE_MANAGER"

class MainActivity : AppCompatActivity() {

    private lateinit var textViewStateManager: TextViewStateManager
    private lateinit var rootView: LinearLayout
    private lateinit var titleTextView: TextView
    private lateinit var changeBtn: Button
    private lateinit var hideBtn: Button
    private lateinit var removeBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setListeners()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(TEXT_VIEW_STATE_MANAGER, textViewStateManager)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.restoreTextViewManager()?.let { this.textViewStateManager = it }
    }

    private fun initViews() {
        rootView = findViewById(R.id.rootLayout)
        titleTextView = findViewById(R.id.titleTextView)
        changeBtn = findViewById(R.id.changeButton)
        hideBtn = findViewById(R.id.hideButton)
        removeBtn = findViewById(R.id.removeButton)
        textViewStateManager = TextViewStateManager(
            textView = titleTextView,
            hideButton = hideBtn,
            removeButton = removeBtn,
        )
    }

    private fun setListeners() {
        changeBtn.setOnClickListener {
            textViewStateManager.changeText(getString(R.string.android_dev))
        }
        hideBtn.setOnClickListener {
            if (titleTextView.isVisible) textViewStateManager.hideView()
            else textViewStateManager.show()
        }
        removeBtn.setOnClickListener {
            textViewStateManager.removeView()
        }
    }

    private fun Bundle.restoreTextViewManager(): TextViewStateManager? {
        return this.getTextViewManager()?.apply {
            restore(
                titleTextView,
                removeBtn,
                hideBtn,
            )
        }
    }

    private fun Bundle.getTextViewManager(): TextViewStateManager? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializable(TEXT_VIEW_STATE_MANAGER, TextViewStateManager::class.java)
        } else getSerializable(TEXT_VIEW_STATE_MANAGER) as? TextViewStateManager
    }
}