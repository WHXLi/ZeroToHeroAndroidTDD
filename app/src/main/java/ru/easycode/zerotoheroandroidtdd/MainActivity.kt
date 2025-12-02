package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val uiHandler = UiHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = ViewModelFactory(LiveDataWrapper.Base(), Repository.Base())
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        setContentView(binding.root)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.actionButton.setOnClickListener { viewModel.load() }
    }

    private fun initObservers() {
        viewModel.stateLiveData().observe(this) { it.apply(uiHandler) }
    }

    private inner class ViewModelFactory(
        val liveDataWrapper: LiveDataWrapper,
        val repository: Repository,
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainViewModel(liveDataWrapper,repository) as T
    }

    private inner class UiHandler() : UiState.Handler() {

        override fun showProgressBar() {
            binding.progressBar.visibility = View.VISIBLE
            binding.actionButton.isEnabled = false
        }

        override fun hideProgressBar() {
            binding.progressBar.visibility = View.GONE
            binding.actionButton.isEnabled = true
        }

        override fun showData() {
            binding.titleTextView.visibility = View.VISIBLE
        }
    }
}