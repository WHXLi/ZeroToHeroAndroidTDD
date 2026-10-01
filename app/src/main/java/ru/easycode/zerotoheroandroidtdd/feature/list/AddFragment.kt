package ru.easycode.zerotoheroandroidtdd.feature.list

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.easycode.zerotoheroandroidtdd.core.view.AbstractBottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentAddBinding

class AddFragment: AbstractBottomSheetDialogFragment<FragmentAddBinding, AddViewModel>(
    bindingInflater = FragmentAddBinding::inflate,
    viewModelClass = AddViewModel::class.java,
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    override fun onBackPressed(action: () -> Unit) = super.onBackPressed {
        viewModel.comeback()
        dismiss()
    }

    private fun initClickListeners() {
        binding.saveButton.setOnClickListener {
            viewModel.add(binding.addInputEditText.text.toString())
            dismiss()
        }
    }
}