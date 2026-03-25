package ru.easycode.zerotoheroandroidtdd.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.fragment.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding

class ListFragment: AbstractFragment<FragmentListBinding>() {
    private lateinit var viewModel: ListViewModel

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentListBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        val viewModelProvider = (activity as ViewModelProvider.Create)
        viewModel = viewModelProvider.createViewModel(ListViewModel::class.java)
        binding.addButton.setOnClickListener { viewModel.create() }
    }
}