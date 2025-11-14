package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

private const val TITLE_TEXT = "title_text"
private const val TITLE_VISIBILITY = "title_visibility"
private const val TITLE_IS_ATTACHED = "title_is_attached"

class MainActivity : AppCompatActivity() {

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
        outState.putString(TITLE_TEXT, titleTextView.text.toString())
        outState.putInt(TITLE_VISIBILITY, titleTextView.visibility)
        outState.putBoolean(TITLE_IS_ATTACHED, titleTextView.isAttachedToWindow)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        restoreTitleTextView(savedInstanceState)
    }

    private fun initViews() {
        rootView = findViewById(R.id.rootLayout)
        titleTextView = findViewById(R.id.titleTextView)
        changeBtn = findViewById(R.id.changeButton)
        hideBtn = findViewById(R.id.hideButton)
        removeBtn = findViewById(R.id.removeButton)
    }

    private fun setListeners() {
        changeBtn.setOnClickListener { titleTextView.text = getString(R.string.android_dev) }
        hideBtn.setOnClickListener { titleTextView.visibility = View.GONE }
        removeBtn.setOnClickListener {
            rootView.removeView(titleTextView)
            removeBtn.isEnabled = false
        }
    }

    private fun restoreTitleTextView(savedInstanceState: Bundle) {
        val notAttached = !savedInstanceState.getBoolean(TITLE_IS_ATTACHED)
        if (notAttached) {
            rootView.removeView(titleTextView)
            removeBtn.isEnabled = false
        } else {
            titleTextView.text = savedInstanceState.getString(TITLE_TEXT)
            titleTextView.visibility = savedInstanceState.getInt(TITLE_VISIBILITY)
        }
    }
}