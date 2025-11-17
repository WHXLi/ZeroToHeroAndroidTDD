package ru.easycode.zerotoheroandroidtdd

import TextViewStateManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import ru.easycode.zerotoheroandroidtdd.count.Count

private const val TITLE_MANAGER = "TITLE_MANAGER"
private const val COUNT_MANAGER = "COUNT_MANAGER"

class MainActivity : AppCompatActivity() {

    private lateinit var titleTextViewManager: TextViewStateManager
    private lateinit var countTextViewManager: TextViewStateManager
    private lateinit var rootView: LinearLayout
    private lateinit var titleTextView: TextView
    private lateinit var changeButton: Button
    private lateinit var hideButton: Button
    private lateinit var removeButton: Button
    private lateinit var countTextView: TextView
    private lateinit var incrementButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setListeners()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(TITLE_MANAGER, titleTextViewManager)
        outState.putSerializable(COUNT_MANAGER, countTextViewManager)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.restoreTextViewManager(TITLE_MANAGER)?.let {
            this.titleTextViewManager = it
        }
        savedInstanceState.restoreTextViewManager(COUNT_MANAGER)?.let {
            this.countTextViewManager = it
        }
    }

    private fun initViews() {
        rootView = findViewById(R.id.rootLayout)
        countTextView = findViewById(R.id.countTextView)
        changeButton = findViewById(R.id.changeButton)
        hideButton = findViewById(R.id.hideButton)
        removeButton = findViewById(R.id.removeButton)
        incrementButton = findViewById(R.id.incrementButton)
        titleTextView = findViewById<TextView>(R.id.titleTextView).apply {
            titleTextViewManager = TextViewStateManager(
                textView = this,
                removeButton = removeButton,
                hideButton = hideButton,
            )
        }
        countTextView = findViewById<TextView>(R.id.countTextView).apply {
            countTextViewManager = TextViewStateManager(this)
        }
    }

    private fun setListeners() {
        changeButton.setOnClickListener {
            titleTextViewManager.changeText(getString(R.string.android_dev))
        }
        hideButton.setOnClickListener {
            if (titleTextView.isVisible) titleTextViewManager.hideView()
            else titleTextViewManager.show()
        }
        removeButton.setOnClickListener {
            titleTextViewManager.removeView()
        }
        incrementButton.setOnClickListener {
            countTextViewManager.changeText(
                Count.Base(2).increment(countTextView.text.toString())
            )
        }
    }

    private fun Bundle.restoreTextViewManager(key: String, ) = this.getTextViewManager(key)?.apply {
        when (key) {
            TITLE_MANAGER -> restore(
                textView = titleTextView,
                removeButton = removeButton,
                hideButton = hideButton,
            )
            COUNT_MANAGER -> restore(countTextView)
        }
    }

    private fun Bundle.getTextViewManager(key: String): TextViewStateManager? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializable(key, TextViewStateManager::class.java)
        } else getSerializable(key) as? TextViewStateManager
    }
}