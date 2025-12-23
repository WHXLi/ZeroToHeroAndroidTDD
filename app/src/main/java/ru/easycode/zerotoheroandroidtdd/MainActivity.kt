package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import java.io.Serializable

private const val RESTORE_KEY = "RESTORE_KEY"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val minInputChars = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.inputEditText.addTextChangedListener { changeButtonEnabled() }
        binding.actionButton.setOnClickListener {
            binding.titleTextView.text = binding.inputEditText.text.toString()
            binding.inputEditText.text?.clear()
            changeButtonEnabled()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(RESTORE_KEY, createRestoreData())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoreData = savedInstanceState.getSerializable(
            RESTORE_KEY,
            RestoreData::class.java
        ) as RestoreData
        restore(restoreData)
    }

    private fun createRestoreData() = RestoreData(
        inputtedText = binding.inputEditText.text.toString(),
        titleText = binding.titleTextView.text.toString(),
    )

    private fun restore(restoreData: RestoreData) {
        binding.inputEditText.setText(restoreData.inputtedText)
        binding.titleTextView.text = restoreData.titleText
        changeButtonEnabled()
    }

    private fun changeButtonEnabled() {
        val inputtedText = binding.inputEditText.text ?: ""
        binding.actionButton.isEnabled = inputtedText.length >= minInputChars
    }

    data class RestoreData(
        val inputtedText: String,
        val titleText: String,
    ): Serializable
}