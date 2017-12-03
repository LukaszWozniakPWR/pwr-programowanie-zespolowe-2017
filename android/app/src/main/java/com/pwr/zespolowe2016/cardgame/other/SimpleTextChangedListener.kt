package com.pwr.zespolowe2016.cardgame.other

import android.text.TextWatcher

abstract class SimpleTextChangedListener : TextWatcher {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }
}