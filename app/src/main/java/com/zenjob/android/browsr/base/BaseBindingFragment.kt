package com.zenjob.android.browsr.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.mohamedabulgasem.loadingoverlay.LoadingAnimation
import com.mohamedabulgasem.loadingoverlay.LoadingOverlay
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.utils.ErrorViewState
import com.zenjob.android.browsr.utils.extensions.showAlertDialog

abstract class BaseBindingFragment<Binding : ViewDataBinding> : Fragment() {
    protected abstract val viewModel: BaseViewModel

    protected lateinit var binding: Binding

    abstract fun onViewBound(binding: Binding)

    @LayoutRes
    abstract fun getLayoutResId(): Int

    private val loadingView by lazy {
        LoadingOverlay.with(requireActivity(), LoadingAnimation.LOADING_SPINNER)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewBound(binding)
        observeOnErrorState()
        setupLoadingView()
    }

    private fun setupLoadingView() {
        viewModel.loadingState.observe(
            viewLifecycleOwner,
            {
                if (it)
                    loadingView.show()
                else
                    loadingView.dismiss()
            }
        )
    }

    private fun observeOnErrorState() {
        viewModel.errorViewState.observe(
            viewLifecycleOwner,
            { it ->
                when (it) {
                    is ErrorViewState.Toast -> {
                        val message = it.message?.let { error ->
                            when (error) {
                                "404" -> getString(R.string.not_found)
                                else -> error
                            }
                        } ?: getString(R.string.empty_state_view_error_title)
                        requireActivity().showAlertDialog(this.getString(R.string.empty_state_view_error_title), message)
                    }
                }
            }
        )
    }
}
