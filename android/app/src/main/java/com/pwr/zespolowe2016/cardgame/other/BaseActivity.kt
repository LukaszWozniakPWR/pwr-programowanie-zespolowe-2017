package com.pwr.zespolowe2016.cardgame.other

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import com.pwr.zespolowe2016.cardgame.R

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutId: Int
    protected abstract val navigation: Navigation

    protected val toolbar: Toolbar by bindView(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    protected open fun handleError(error: Throwable) {
        //TODO error handling
        Log.d("Error", error.toString())
        Log.d("Error", error.printStackTrace().toString())
    }
}