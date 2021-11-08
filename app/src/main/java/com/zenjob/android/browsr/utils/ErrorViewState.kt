package com.zenjob.android.browsr.utils

sealed class ErrorViewState(val message: String?) {
    // for now we show the error in toast may be later we have another type to handle error
    class Toast(message: String? = "") : ErrorViewState(message)
}
