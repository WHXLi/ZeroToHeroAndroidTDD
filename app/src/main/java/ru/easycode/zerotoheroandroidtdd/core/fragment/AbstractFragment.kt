package ru.easycode.zerotoheroandroidtdd.core.fragment

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelProvider

abstract class AbstractFragment<B: ViewBinding>: Fragment() {
    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected lateinit var viewModelProvider: ViewModelProvider.Create

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bind(inflater,container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelProvider = (activity as ViewModelProvider.Create)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): B

    protected fun onBackPressed(action: () -> Unit) {
        requireActivity().onBackPressedDispatcher.addCallback(
            owner = viewLifecycleOwner,
            onBackPressedCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = action()
            },
        )
    }

    protected fun hideKeyboard() {
        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}