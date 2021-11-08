package com.zenjob.android.browsr.base

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zenjob.android.browsr.utils.ErrorViewState
import com.zenjob.android.browsr.utils.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    protected val _loadingState: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _errorViewState: SingleLiveEvent<ErrorViewState> = SingleLiveEvent()
    val errorViewState: LiveData<ErrorViewState>
        get() = _errorViewState

    fun setErrorState(errorState: ErrorViewState) {
        _errorViewState.postValue(errorState)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getErrorStateMessage(): ErrorViewState? {
        return _errorViewState.value
    }

    fun showLoading() {
        _loadingState.postValue(true)
    }

    fun hideLoading() {
        _loadingState.postValue(false)
    }
}
