package com.zenjob.android.browsr.utils.extensions

import android.app.AlertDialog
import android.content.Context

fun Context.showAlertDialog(
    title: String? = "",
    message: String? = "",
    positiveActionTitle: String? = null,
    negativeActionTitle: String? = null,
    isCancelable: Boolean = true,
    positiveAction: (() -> Unit)? = null,
    negativeAction: (() -> Unit)? = null
) {

    // setup the alert builder
    val builder = AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(message)

        // add a button
        positiveActionTitle?.let {
            setPositiveButton(positiveActionTitle) { dialog, _ ->
                dialog.dismiss()
                positiveAction?.invoke()
            }
        }

        negativeActionTitle?.let {
            setNegativeButton(negativeActionTitle) { dialog, _ ->
                dialog.dismiss()
                negativeAction?.invoke()
            }
        }

        setCancelable(isCancelable)
    }

    // create and show the alert dialog
    val dialog: AlertDialog = builder.create()
    dialog.show()
}
