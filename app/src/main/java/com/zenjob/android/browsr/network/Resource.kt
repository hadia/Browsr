package com.zenjob.android.browsr.network

import com.zenjob.android.browsr.base.BaseViewModel
import com.zenjob.android.browsr.utils.ErrorViewState

data class Resource<out T>(val status: Status?, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }
    }
}

fun <T> Resource<T>.checkResponse(
    viewModel: BaseViewModel,
    onSuccessAction: (data: T?) -> Unit,
    onErrorAction: ((error: String?) -> Unit)? = null
) {
    when (this.status) {
        Resource.Status.SUCCESS -> {
            onSuccessAction.invoke(this.data)
            viewModel.hideLoading()
        }
        Resource.Status.ERROR -> {
            onErrorAction?.let {
                onErrorAction.invoke(this.message)
                return
            }

            viewModel.setErrorState(ErrorViewState.Toast(this.message))
            viewModel.hideLoading()
        }
    }
}
