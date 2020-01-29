package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    return isKeyboardClosedOrOpen()
}

fun Activity.isKeyboardClosed(): Boolean {
    return !isKeyboardClosedOrOpen()

}

fun isKeyboardClosedOrOpen(): Boolean {

    return true
}
