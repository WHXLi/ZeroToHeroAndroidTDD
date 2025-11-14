package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

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
        val textViewStateManager = savedInstanceState.getSerializable(
            TEXT_VIEW_STATE_MANAGER,
            TextViewStateManager::class.java
        ) as TextViewStateManager
        this.textViewStateManager = textViewStateManager
    }

    private fun initViews() {
        rootView = findViewById(R.id.rootLayout)
        titleTextView = findViewById(R.id.titleTextView)
        changeBtn = findViewById(R.id.changeButton)
        hideBtn = findViewById(R.id.hideButton)
        removeBtn = findViewById(R.id.removeButton)
        textViewStateManager = TextViewStateManager(
            rootView = rootView,
            textView = titleTextView,
            hideButton = hideBtn,
            removeButton = removeBtn,
        )
    }

    private fun setListeners() {
        changeBtn.setOnClickListener {
            textViewStateManager.change(getString(R.string.android_dev))
        }
        hideBtn.setOnClickListener {
            textViewStateManager.hideView()
        }
        removeBtn.setOnClickListener {
            textViewStateManager.removeView()
        }
    }
}