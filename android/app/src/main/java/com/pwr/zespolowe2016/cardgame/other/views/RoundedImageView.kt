package com.pwr.zespolowe2016.cardgame.other.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.util.AttributeSet
import android.widget.ImageView

class RoundedImageView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    init {
        setWillNotDraw(false)
    }

    override fun setImageDrawable(drawable: Drawable?) {
        val bitmap = bitmapFromDrawable(drawable)
        if (bitmap == null) {
            super.setImageDrawable(null)
            return
        }

        val resources = resources
        val roundedBitmapDrawable = RoundedBitmapDrawableFactory
                .create(resources, bitmap)
        roundedBitmapDrawable.isCircular = true

        super.setImageDrawable(roundedBitmapDrawable)
    }

    override fun setBackgroundColor(color: Int) {
        setImageDrawable(ColorDrawable(color))
    }

    override fun setImageResource(resId: Int) {
        val drawable = ContextCompat.getDrawable(context, resId)
        setImageDrawable(drawable)
    }

    companion object {

        private fun bitmapFromDrawable(drawable: Drawable?): Bitmap? {
            if (drawable == null) {
                return null
            }
            if (drawable is BitmapDrawable) {
                return drawable.bitmap
            }

            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
                    drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }
    }
}