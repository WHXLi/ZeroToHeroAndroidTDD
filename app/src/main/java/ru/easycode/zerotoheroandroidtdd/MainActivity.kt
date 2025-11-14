package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

private const val TITLE_TEXT = "title_text"
private const val TITLE_VISIBILITY = "title_visibility"

class MainActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var changeBtn: Button
    private lateinit var hideBtn: Button

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
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            titleTextView.text = it.getString(TITLE_TEXT)
            titleTextView.visibility = it.getInt(TITLE_VISIBILITY)
        }
    }

    private fun initViews() {
        titleTextView = findViewById(R.id.titleTextView)
        changeBtn = findViewById(R.id.changeButton)
        hideBtn = findViewById(R.id.hideButton)
    }

    private fun setListeners() {
        changeBtn.setOnClickListener { titleTextView.text = getString(R.string.android_dev) }
        hideBtn.setOnClickListener { titleTextView.visibility = View.GONE }
    }
}