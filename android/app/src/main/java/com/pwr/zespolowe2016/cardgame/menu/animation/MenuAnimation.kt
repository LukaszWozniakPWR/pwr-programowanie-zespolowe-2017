package com.pwr.zespolowe2016.cardgame.menu.animation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.drawable.TransitionDrawable
import android.view.Menu
import android.view.View
import android.view.animation.Interpolator
import android.widget.*

class MenuAnimation {

    private val mainContainer: LinearLayout
    private val appLogoImageView: ImageView
    private val viewAnimator: ViewAnimator
    private val endAnimatorListener: OnEndAnimatorListener
    private val menuAnimationAttributes: MenuAnimationAttributes

    private var heightOfScreen: Float = 0f
    private var endPointOfFirstAppLogoAnimation = 0f
    private val endPointOfSignInAndTermsAnimation = 0f

    private constructor(mainContainer: LinearLayout,
                        appLogoImageView: ImageView,
                        viewAnimator: ViewAnimator,
                        endAnimatorListener: OnEndAnimatorListener,
                        menuAnimationAttributes: MenuAnimationAttributes) {
        this.mainContainer = mainContainer
        this.appLogoImageView = appLogoImageView
        this.viewAnimator = viewAnimator
        this.endAnimatorListener = endAnimatorListener
        this.menuAnimationAttributes = menuAnimationAttributes
    }

    fun startAnimations() {
        getNeededValues()
        moveViewsBeyondScreen()
        startFirstAppLogoAnimation()
    }

    private fun getNeededValues() {
        heightOfScreen = mainContainer.height.toFloat()
        endPointOfFirstAppLogoAnimation = topOfCenteredAppLogoImage()
    }

    private fun topOfCenteredAppLogoImage(): Float {
        return (heightOfScreen / 2f) - (appLogoImageView.height / 2f) - appLogoImageView.top
    }

    private fun moveViewsBeyondScreen() {
        moveAppLogoImageViewAboveTopOfScreen()
        moveSignInAndTermsContainerBelowBottomOfScreen()
    }

    private fun moveAppLogoImageViewAboveTopOfScreen() {
        appLogoImageView.y = -appLogoImageView.height.toFloat()
    }

    private fun moveSignInAndTermsContainerBelowBottomOfScreen() {
        viewAnimator.y = heightOfScreen
    }

    private fun startFirstAppLogoAnimation() {
        startSpecificAnimator(
                appLogoImageView,
                endPointOfFirstAppLogoAnimation,
                SpringInterpolator(menuAnimationAttributes.springInterpolatorFactor),
                menuAnimationAttributes.durationOfFirstAppLogoAnimation,
                menuAnimationAttributes.startDelayOfFirstAppLogoAnimation,
                getFirstAppLogoAnimationAnimatorListener()
        )
    }

    private fun getFirstAppLogoAnimationAnimatorListener(): OnEndAnimatorListener {
        return object : OnEndAnimatorListener() {
            override fun onAnimationEnd(animation: Animator) {
                startCommonViewsAnimation()
            }
        }
    }

    private fun startCommonViewsAnimation() {
        startCommonAnimation(appLogoImageView, getSecondAppLogoAnimationAnimatorListener())
        startCommonAnimation(viewAnimator)
    }

    private fun getSecondAppLogoAnimationAnimatorListener(): OnStartAnimatorListener {
        return object : OnStartAnimatorListener() {
            override fun onAnimationStart(animation: Animator) {
                startBackgroundAnimation()
            }
        }
    }

    private fun startBackgroundAnimation() {
        val transition: TransitionDrawable = mainContainer.background as TransitionDrawable
        transition.startTransition(menuAnimationAttributes.durationOfCommonViewsAnimation.toInt())
    }

    private fun startCommonAnimation(view: View) {
        startCommonAnimation(view, endAnimatorListener)
    }

    private fun startCommonAnimation(view: View, animatorListener: Animator.AnimatorListener) {
        startSpecificAnimator(
                view,
                endPointOfSignInAndTermsAnimation,
                SpringInterpolator(menuAnimationAttributes.springInterpolatorFactor),
                menuAnimationAttributes.durationOfCommonViewsAnimation,
                menuAnimationAttributes.startDelayOfCommonViewsAnimation,
                animatorListener
        )
    }

    private fun startSpecificAnimator(view: View,
                                      endPoint: Float,
                                      interpolator: Interpolator,
                                      duration: Long,
                                      startDelay: Long,
                                      animatorListener: Animator.AnimatorListener) {
        val animator: Animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, endPoint)
        animator.interpolator = interpolator
        animator.duration = duration
        animator.startDelay = startDelay
        animator.addListener(animatorListener)
        animator.start()
    }

    class Builder() {

        var menuAnimationAttributes: MenuAnimationAttributes = MenuAnimationAttributes(
                DEFAULT_SPRING_INTERPOLATOR_FACTOR,
                DEFAULT_DURATION_OF_FIRST_APP_LOGO_ANIMATION,
                DEFAULT_START_DELAY_OF_FIRST_APP_LOGO_ANIMATION,
                DEFAULT_DURATION_OF_COMMON_VIEWS_ANIMATION,
                DEFAULT_START_DELAY_OF_COMMON_VIEWS_ANIMATION)

        fun build(mainContainer: LinearLayout, appLogoImageView: ImageView,
                  viewAnimator: ViewAnimator, endAnimatorListener: OnEndAnimatorListener): MenuAnimation {
            return MenuAnimation(mainContainer, appLogoImageView,
                    viewAnimator, endAnimatorListener, menuAnimationAttributes)
        }

        fun springInterpolatorFactor(springInterpolatorFactor: Float): Builder {
            menuAnimationAttributes.springInterpolatorFactor = springInterpolatorFactor
            return this
        }

        fun durationOfFirstAppLogoAnimation(durationOfFirstAppLogoAnimation: Long): Builder {
            menuAnimationAttributes.durationOfFirstAppLogoAnimation = durationOfFirstAppLogoAnimation
            return this
        }

        fun startDelayOfFirstAppLogoAnimation(startDelayOfFirstAppLogoAnimation: Long): Builder {
            menuAnimationAttributes.startDelayOfFirstAppLogoAnimation = startDelayOfFirstAppLogoAnimation
            return this
        }

        fun durationOfCommonViewsAnimation(durationOfCommonViewsAnimation: Long): Builder {
            menuAnimationAttributes.durationOfCommonViewsAnimation = durationOfCommonViewsAnimation
            return this
        }

        fun startDelayOfCommonViewsAnimation(startDelayOfCommonViewsAnimation: Long): Builder {
            menuAnimationAttributes.startDelayOfCommonViewsAnimation = startDelayOfCommonViewsAnimation
            return this
        }
    }

    companion object {
        private val DEFAULT_SPRING_INTERPOLATOR_FACTOR = 0.4f
        private val DEFAULT_DURATION_OF_FIRST_APP_LOGO_ANIMATION = 1000L
        private val DEFAULT_START_DELAY_OF_FIRST_APP_LOGO_ANIMATION = 1500L
        private val DEFAULT_DURATION_OF_COMMON_VIEWS_ANIMATION = 800L
        private val DEFAULT_START_DELAY_OF_COMMON_VIEWS_ANIMATION = 700L
    }
}