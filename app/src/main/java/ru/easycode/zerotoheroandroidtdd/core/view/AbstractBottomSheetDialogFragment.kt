package ru.easycode.zerotoheroandroidtdd.core.view

import android.app.Dialog
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class AbstractBottomSheetDialogFragment<VB: ViewBinding, T: ViewModel>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> VB,
    private val viewModelClass: Class<T>,
): BottomSheetDialogFragment() {

    private val delegate = FragmentDelegate(
        bindingInflater = bindingInflater,
        viewModelClass = viewModelClass,
    )
    protected val binding get() = delegate.binding
    protected val viewModel get() = delegate.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.onCreate(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = delegate.onCreateView(inflater)

    override fun onDestroyView() {
        super.onDestroyView()
        delegate.onDestroyView()
    }

    open fun onBackPressed(action: () -> Unit) {
        delegate.onBackPressed(
            fragment = this,
            dialog = this.dialog as BottomSheetDialog,
        ) { action() }
    }
}