package com.pwr.zespolowe2016.cardgame.other

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.pwr.zespolowe2016.cardgame.R
import kotlin.properties.ReadOnlyProperty

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutId: Int
    protected abstract val navigation: Navigation

    protected val toolbar: Toolbar by bindView(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        setSupportActionBar(toolbar)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    protected open fun handleError(error: Throwable) {
        //TODO error handling
        Log.d("Error", error.toString())
        Log.d("Error", error.printStackTrace().toString())
    }

    private val dimensionFinder: BaseActivity.(Int) -> Int?
        get() = { resources?.getDimensionPixelSize(it) }

    private val stringFinder: BaseActivity.(Int) -> String?
        get() = { resources?.getString(it) }

    private val colorFinder: BaseActivity.(Int) -> Int?
        get() = { resources?.getInteger(it) }

    private val drawableFinder: BaseActivity.(Int) -> Drawable?
        get() = { android.support.v4.content.ContextCompat.getDrawable(this, it) }

    private val integerFinder: BaseActivity.(Int) -> Int?
        get() = { id -> resources.getInteger(id) }

    private val fractionFinder: BaseActivity.(Int) -> Float?
        get() = { id -> resources.getFraction(id, 1, 1 /*Defaults*/) }

    private val parentFractionFinder: BaseActivity.(Int, Int, Int) -> Float?
        get() = { id, base, pBase -> resources.getFraction(id, base, pBase) }

    private val boolFinder: BaseActivity.(Int) -> Boolean?
        get() = { id -> resources.getBoolean(id) }

    protected fun <V : View> BaseActivity.bindView(id: Int)
            : ReadOnlyProperty<BaseActivity, V> {
        return required(id, viewFinder)
    }

    protected fun BaseActivity.bindDimen(id: Int)
            : ReadOnlyProperty<BaseActivity, Int> {
        return requiredDimen(id, dimensionFinder)
    }

    protected fun BaseActivity.bindString(id: Int)
            : Lazy<BaseActivity, String> {
        return requiredString(id, stringFinder)
    }

    protected fun BaseActivity.bindColor(id: Int)
            : Lazy<BaseActivity, Int> {
        return requiredColor(id, colorFinder)
    }

    protected fun BaseActivity.bindDrawable(id: Int)
            : Lazy<BaseActivity, Drawable> {
        return requiredDrawable(id, drawableFinder)
    }

    protected fun BaseActivity.bindInteger(id: Int): Lazy<BaseActivity, Int> {
        return requiredInt(id, integerFinder)
    }

    protected fun BaseActivity.bindFloat(id: Int)
            : Lazy<BaseActivity, Float> {
        return requiredFraction(id, fractionFinder)
    }

    protected fun BaseActivity.bindFloat(id: Int, base: Int, parentBase: Int)
            : Lazy<BaseActivity, Float> {
        return requiredFraction(id, base, parentBase, parentFractionFinder)
    }

    protected fun BaseActivity.bindBool(id: Int)
            : Lazy<BaseActivity, Boolean> {
        return requiredBool(id, boolFinder)
    }
}