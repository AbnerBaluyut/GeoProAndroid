package com.letsbee.geoproandroid.common.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast

/** CONTEXT **/
fun Context.toast(message: CharSequence, isLengthLong: Boolean = true) =  Toast.makeText(
    this, message, if (isLengthLong) {
        Toast.LENGTH_LONG
    } else {
        Toast.LENGTH_SHORT
    }
).show()

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}


/** EDIT TEXT **/
fun EditText.isEmpty() : Boolean = this.text.toString().trim().isEmpty() || this.text.toString().trim().isBlank()
fun EditText.isNotEmpty() : Boolean = this.text.toString().trim().isNotEmpty() || this.text.toString().trim().isNotBlank()

