package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var loadButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListeners()
    }

    private fun initViews() {
        titleTextView = findViewById(R.id.titleTextView)
        loadButton = findViewById(R.id.actionButton)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun initListeners() {
        loadButton.setOnClickListener { showProgressBar(3500L) }
    }

    private fun showProgressBar(time: Long) = lifecycleScope.launch {
        loadButton.isEnabled = false
        progressBar.visibility = View.VISIBLE
        delay(time)
        progressBar.visibility = View.GONE
        titleTextView.visibility = View.VISIBLE
        loadButton.isEnabled = true
    }
}