package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data = savedInstanceState.getSerializable(
            RESTORE_KEY,
            RestoreData::class.java
        ) as RestoreData
        restore(data)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(RESTORE_KEY, createRestoreData())
    }

    private fun initObservers() {
        binding.actionButton.setOnClickListener { setInputtedText() }
    }

    private fun setInputtedText() {
        binding.titleTextView.text = binding.inputEditText.text
        binding.inputEditText.text?.clear()
    }

    private fun createRestoreData() = RestoreData(
        inputtedText = binding.inputEditText.text.toString(),
        inputOnFocus = binding.inputEditText.hasFocus(),
        titleText = binding.titleTextView.text.toString(),
    )

    private fun restore(restoreData: RestoreData) {
        binding.titleTextView.text = restoreData.titleText
        binding.inputEditText.setText(restoreData.inputtedText)
        if (restoreData.inputOnFocus) binding.inputEditText.requestFocus()
    }
}