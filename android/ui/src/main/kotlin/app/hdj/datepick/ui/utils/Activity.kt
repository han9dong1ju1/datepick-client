package app.hdj.datepick.ui.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity

fun Context.getActivity(): AppCompatActivity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is AppCompatActivity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    return null
}

fun Activity.isPermissionGranted(permission: String) = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED