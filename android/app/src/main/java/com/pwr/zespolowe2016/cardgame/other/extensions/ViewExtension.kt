package com.pwr.zespolowe2016.cardgame.other.extensions

import android.content.Context
import android.content.res.TypedArray
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ViewAnimator
import com.pwr.zespolowe2016.cardgame.other.DialogCreator
import com.pwr.zespolowe2016.cardgame.other.Lazy
import com.pwr.zespolowe2016.cardgame.other.requiredString

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

val EditText.string: String
    get() = this.text.toString()

fun EditText.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun EditText.showKeyboard() {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun ViewAnimator.displayChild(childIndex: Int) {
    if (displayedChild != childIndex) displayedChild = childIndex
}

fun SwipeRefreshLayout.setRefreshingSafely(refreshing: Boolean) {
    if (isRefreshing == refreshing) return
    isRefreshing = refreshing
}

val View.stringFinder: View.(Int) -> String?
    get() = { context.resources?.getString(it) }

private fun View.bindString(id: Int): Lazy<View, String> {
    return requiredString(id, stringFinder)
}

fun AttributeSet.extractAttributes(context: Context, attributes: IntArray, applyAttributes: (TypedArray)-> Unit) {
    val typedArray = context.obtainStyledAttributes(this, attributes, 0, 0)
    try {
        applyAttributes(typedArray)
    } finally {
        typedArray.recycle()
    }
}