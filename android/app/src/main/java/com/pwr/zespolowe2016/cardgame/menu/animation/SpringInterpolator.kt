package com.pwr.zespolowe2016.cardgame.menu.animation

import android.view.animation.Interpolator

open class SpringInterpolator(private val resistanceFactor: Float) : Interpolator {

    override fun getInterpolation(input: Float): Float {
        return (performExponentiation(input) * calculateSinus(input) + 1).toFloat()
    }

    private fun performExponentiation(input: Float): Double {
        return Math.pow(2.0, (-10 * input).toDouble())
    }

    private fun calculateSinus(input: Float): Double {
        return Math.sin((input - resistanceFactor / 4) * divideDoubleNumberPIByFactor())
    }

    private fun divideDoubleNumberPIByFactor(): Double {
        return ((2 * Math.PI) / resistanceFactor).toDouble()
    }
}
