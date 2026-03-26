package ru.easycode.zerotoheroandroidtdd.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.bundle.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.core.fragment.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding

class ListFragment: AbstractFragment<FragmentListBinding>() {
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: ListAdapter

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentListBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerView()
        initListeners()
        initObservers()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { viewModel.restore(BundleWrapper.Base(it)) }
    }

    private fun initViewModel() {
        viewModel = viewModelProvider.createViewModel(ListViewModel::class.java)
    }

    private fun initRecyclerView() {
        adapter = ListAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun initListeners() {
        binding.addButton.setOnClickListener { viewModel.create() }
    }

    private fun initObservers() {
        viewModel.liveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }
    }
}