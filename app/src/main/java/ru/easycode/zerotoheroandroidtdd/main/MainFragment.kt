package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.core.view.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentMainBinding
import ru.easycode.zerotoheroandroidtdd.feature.list.ListAdapter


class MainFragment: AbstractFragment<FragmentMainBinding, MainViewModel>(
    bindingInflater = FragmentMainBinding::inflate,
    viewModelClass = MainViewModel::class.java,
) {

    private val adapter = ListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        viewModel.init()
        initObservers()
        initClickListeners()
    }

    private fun initObservers() {
        viewModel.liveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }
    }

    private fun initClickListeners() {
        binding.addButton.setOnClickListener {
            viewModel.navigateToAdd()
        }
    }
}