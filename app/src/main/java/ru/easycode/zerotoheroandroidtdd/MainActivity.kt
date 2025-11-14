package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView

private const val TITLE = "title"

class MainActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var changeBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titleTextView = findViewById(R.id.titleTextView)
        changeBtn = findViewById(R.id.changeButton)
        savedInstanceState?.let {
            titleTextView.text = it.getString(TITLE)
        }
        changeBtn.setOnClickListener { titleTextView.text = getString(R.string.android_dev) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TITLE, titleTextView.text.toString())
    }
}