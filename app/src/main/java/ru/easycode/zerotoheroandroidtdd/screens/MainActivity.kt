package ru.easycode.zerotoheroandroidtdd.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var textListAdapter: TextListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initTextListRv()
        initListeners()
        initObservers()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.restore(BundleWrapper.Base(savedInstanceState))
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            MainViewModel.Factory()
        )[MainViewModel::class.java]
    }

    private fun initTextListRv() {
        textListAdapter = TextListAdapter()
        binding.recyclerView.adapter = textListAdapter
    }

    private fun initListeners() {
        binding.actionButton.setOnClickListener {
            binding.inputEditText.text?.let {
                viewModel.add(it.toString())
                it.clear()
            }
        }
    }

    private fun initObservers() {
        viewModel.getLiveData().observe(this) {
            textListAdapter.update(it)
        }
    }
}