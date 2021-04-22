package com.letsbee.geoproandroid.utils

import android.content.Context
import android.widget.Toast

fun Context.toast(message: CharSequence, isLengthLong: Boolean = true) =
    Toast.makeText(
        this, message, if (isLengthLong) {
            Toast.LENGTH_LONG
        } else {
            Toast.LENGTH_SHORT
        }
    ).show()