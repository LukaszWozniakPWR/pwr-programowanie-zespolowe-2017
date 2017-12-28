package com.pwr.zespolowe2016.cardgame.menu.animation

import android.animation.Animator

abstract class OnEndAnimatorListener : Animator.AnimatorListener {

    override fun onAnimationStart(animation: Animator) {
    }

    override fun onAnimationCancel(animation: Animator) {
    }

    override fun onAnimationRepeat(animation: Animator) {
    }

    companion object {
        val EMPTY = object : OnEndAnimatorListener() {
            override fun onAnimationEnd(animation: Animator) {
            }

            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        }
    }
}