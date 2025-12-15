package ru.easycode.zerotoheroandroidtdd.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.App
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.remote.Repository
import ru.easycode.zerotoheroandroidtdd.remote.TASK_URL
import ru.easycode.zerotoheroandroidtdd.state.UiState
import ru.easycode.zerotoheroandroidtdd.wrapper.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.wrapper.LiveDataWrapper

class MainActivity : AppCompatActivity(), UiState.Handler {

    private val app = App()
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initObserver()
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
            owner = this,
            factory = MainViewModelFactory(),
        )[MainViewModel::class.java]
    }

    private fun initObserver() {
        viewModel.uiStateLiveData().observe(this) { it.apply(this) }
        binding.actionButton.setOnClickListener { viewModel.load() }
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.actionButton.isEnabled = false
    }

    override fun showData(text: String) {
        binding.titleTextView.text = text
        binding.titleTextView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.actionButton.isEnabled = true
    }

    private inner class MainViewModelFactory(): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val liveDataWrapper = LiveDataWrapper.Base()
            val repository = Repository.Base(app.service, TASK_URL)
            return MainViewModel(liveDataWrapper, repository) as T
        }
    }
}