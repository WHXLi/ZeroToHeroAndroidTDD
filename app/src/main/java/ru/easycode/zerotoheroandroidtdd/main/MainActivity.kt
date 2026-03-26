package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ViewModelProvider.Create {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel(savedInstanceState == null)
    }

    override fun <T : ViewModel> createViewModel(viewModelClass: Class<T>): T {
        return (application as ViewModelProvider.Create).createViewModel(viewModelClass)
    }

    private fun initViewModel(firstRun: Boolean) {
        viewModel = createViewModel(MainViewModel::class.java)
        viewModel.liveData().observe(this) {
            it.show(supportFragmentManager, binding.container.id)
        }
        viewModel.init(firstRun)
    }
}