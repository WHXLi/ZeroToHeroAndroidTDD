package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.count.Count
import ru.easycode.zerotoheroandroidtdd.state.UiState

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var rootView: LinearLayout
    private lateinit var titleTextView: TextView
    private lateinit var changeButton: Button
    private lateinit var hideButton: Button
    private lateinit var removeButton: Button
    private lateinit var countTextView: TextView
    private lateinit var incrementButton: Button
    private lateinit var decrementButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(R.layout.activity_main)
        initViews()
        initObservers()
        initBaseCount()
        setListeners()
    }

    private fun initViews() {
        rootView = findViewById(R.id.rootLayout)
        titleTextView = findViewById(R.id.titleTextView)
        countTextView = findViewById(R.id.countTextView)
        changeButton = findViewById(R.id.changeButton)
        hideButton = findViewById(R.id.hideButton)
        removeButton = findViewById(R.id.removeButton)
        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)
    }

    private fun initObservers() {
        viewModel.getUiState().observe(this) { handleUiState(it) }
    }

    private fun initBaseCount() {
        val baseCount = viewModel.getCount() as? Count.Base
        baseCount?.let { viewModel.initial(it.current.toString(), it) }
    }

    private fun setListeners() {
        changeButton.setOnClickListener {

        }
        hideButton.setOnClickListener {

        }
        removeButton.setOnClickListener {

        }
        incrementButton.setOnClickListener {
            viewModel.increment(countTextView.text.toString())
        }
        decrementButton.setOnClickListener {
            viewModel.decrement(countTextView.text.toString())
        }
    }

    private fun handleUiState(uiState: UiState) = when (uiState) {
        is UiState.Base -> {
            countTextView.text = uiState.text
            incrementButton.isEnabled = true
            decrementButton.isEnabled = true
        }
        is UiState.Max -> {
            countTextView.text = uiState.text
            incrementButton.isEnabled = false
            decrementButton.isEnabled = true
        }
        is UiState.Min -> {
            countTextView.text = uiState.text
            incrementButton.isEnabled = true
            decrementButton.isEnabled = false
        }
    }
}