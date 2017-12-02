package com.pwr.zespolowe2016.cardgame.menu

import android.os.Bundle
import android.widget.Button
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.BaseActivity
import com.pwr.zespolowe2016.cardgame.other.Navigation
import com.pwr.zespolowe2016.cardgame.other.bindView

class MenuActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_menu
    override val navigation = Navigation(this)

    private val startButton: Button by bindView(R.id.start_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
}