package com.coppel.apkqa001inspecciones.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(context: Context, message: String) {
    val showToast = true
    if (showToast) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

fun Activity.showToast(context: Context, message: String) {
    val showToast = true
    if (showToast) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
