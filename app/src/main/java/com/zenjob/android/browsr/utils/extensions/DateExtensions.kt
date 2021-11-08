package com.zenjob.android.browsr.utils.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun Long.longTimestampToDateFormat(): String = SimpleDateFormat("MMM dd, yyyy", Locale.US).format(this)
