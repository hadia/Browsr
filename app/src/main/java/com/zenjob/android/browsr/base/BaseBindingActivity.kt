package com.zenjob.android.browsr.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mohamedabulgasem.loadingoverlay.LoadingAnimation
import com.mohamedabulgasem.loadingoverlay.LoadingOverlay
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.utils.ErrorViewState
import com.zenjob.android.browsr.utils.extensions.showAlertDialog

abstract class BaseBindingActivity <Binding : ViewDataBinding> : AppCompatActivity() {

    protected abstract val viewModel: BaseViewModel

    protected lateinit var binding: Binding

    abstract fun onViewBound(binding: Binding)

    @LayoutRes
    abstract fun getLayoutResId(): Int

    private val loadingView by lazy {
        LoadingOverlay.with(this, LoadingAnimation.LOADING_SPINNER)
    }

    protected open fun inflateContentView(): View {
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutResId(), null, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(inflateContentView())
        onViewBound(binding)
        observeOnErrorState()
        setupLoadingView()
    }

    private fun setupLoadingView() {
        viewModel.loadingState.observe(
            this,
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
            this,
            {
                when (it) {
                    is ErrorViewState.Toast -> {
                        val message = it.message?.let { error ->
                            when (error) {
                                "404" -> getString(R.string.not_found)
                                else -> error
                            }
                        } ?: getString(R.string.empty_state_view_error_title)
                        this.showAlertDialog(this.getString(R.string.empty_state_view_error_title), message)
                    }
                }
            }
        )
    }
}
