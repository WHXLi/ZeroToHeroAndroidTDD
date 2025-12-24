package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

private const val ADDED_TEXT_UNITS_KEY = "ADDED_TEXT_KEY"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.actionButton.setOnClickListener {
            createListElement(binding.inputEditText.text.toString())
            binding.inputEditText.text?.clear()
        }
        binding.inputEditText.addTextChangedListener {
            binding.actionButton.isEnabled = it.toString().isNotEmpty()
        }
    }

    private fun createListElement(text: String) {
        binding.contentLayout.addView(
            TextView(this).apply { this.text = text }
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getStringArray(ADDED_TEXT_UNITS_KEY)?.let {
            it.forEach { text -> createListElement(text) }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val textViews = binding.contentLayout.allViews.filterIsInstance<TextView>()
        val texts = textViews.mapNotNull { it.text?.toString() }.toList().toTypedArray()
        outState.putStringArray(ADDED_TEXT_UNITS_KEY, texts)
    }
}
