package ru.easycode.zerotoheroandroidtdd.core.view

import android.view.LayoutInflater
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelProvider

class FragmentDelegate<VB: ViewBinding, VM: ViewModel>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> VB,
    private val viewModelClass: Class<VM>
) {

    val binding: VB
        get() = _binding!!
    private var _binding: VB? = null
    lateinit var viewModel: VM
        private set
    var onBackPressedCallback: OnBackPressedCallback? = null
        private set

    fun onCreate(fragment: Fragment) {
        val application = fragment.requireActivity().application as ViewModelProvider.Provide
        viewModel = application.create(viewModelClass)
    }

    fun onCreateView(inflater: LayoutInflater): View {
        _binding = bindingInflater(inflater)
        return binding.root
    }

    fun onDestroyView() {
        _binding = null
        onBackPressedCallback?.remove()
    }

    fun onBackPressed(
        fragment: Fragment,
        dialog: BottomSheetDialog? = null,
        action: () -> Unit,
    ) {
        onBackPressedCallback = createOnBackPressedCallback { action() }
        onBackPressedCallback?.let {
            val onBackPressedDispatcher = dialog?.onBackPressedDispatcher
                ?: fragment.requireActivity().onBackPressedDispatcher
            onBackPressedDispatcher.addCallback(it)
        }
    }

    private fun createOnBackPressedCallback(
        action: () -> Unit
    ) = object : OnBackPressedCallback(true) {

        override fun handleOnBackPressed() { action() }
    }
}