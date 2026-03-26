package ru.easycode.zerotoheroandroidtdd.feature.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.fragment.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateBinding

class CreateFragment : AbstractFragment<FragmentCreateBinding>() {
    private lateinit var viewModel: CreateViewModel

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCreateBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initListeners()
        onBackPressed { viewModel.comeback() }
    }

    private fun initViewModel() {
        viewModel = viewModelProvider.createViewModel(CreateViewModel::class.java)
    }

    private fun initListeners() {
        binding.inputEditText.addTextChangedListener {
            binding.createButton.isEnabled = it.toString().length >= 3
        }
        binding.createButton.setOnClickListener {
            hideKeyboard()
            viewModel.add(binding.inputEditText.text.toString())
        }
    }
}