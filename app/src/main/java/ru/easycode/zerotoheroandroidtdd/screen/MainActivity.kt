package ru.easycode.zerotoheroandroidtdd.screen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.App
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.state.UiState
import ru.easycode.zerotoheroandroidtdd.wrapper.BundleWrapper

class MainActivity : AppCompatActivity(), UiState.Handler {

    private val app = App()
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initObservers()
    }

    override fun showProgress() {
        binding.actionButton.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE
        binding.titleTextView.visibility = View.GONE
    }

    override fun showData(text: String) {
        binding.titleTextView.visibility = View.VISIBLE
        binding.titleTextView.text = text
        binding.progressBar.visibility = View.GONE
        binding.actionButton.isEnabled = true
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.restore(BundleWrapper.Base(savedInstanceState))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            MainViewModel.Factory(app.service)
        )[MainViewModel::class.java]
    }

    private fun initObservers() {
        viewModel.getUiStateLiveData().observe(this) { it.apply(this) }
        binding.actionButton.setOnClickListener { viewModel.load() }
    }
}