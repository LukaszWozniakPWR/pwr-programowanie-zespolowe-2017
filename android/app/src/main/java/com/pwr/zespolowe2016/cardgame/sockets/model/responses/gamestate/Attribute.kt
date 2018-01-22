package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.pwr.zespolowe2016.cardgame.R

enum class Attribute(@StringRes val attributeName: Int, @StringRes val attributeDescription: Int, @DrawableRes val attributeIcon: Int) {
    COLD(R.string.cold_attribute_name, R.string.cold_attribute_description, 0),
    RAIN(R.string.rain_attribute_name, R.string.rain_attribute_description, 0),
    FOG(R.string.fog_attribute_name, R.string.fog_attribute_description, 0),
    GOOD_WEATHER(R.string.good_weather_attribute_name, R.string.good_weather_attribute_description, 0),
    COMMANDERS_HORN(R.string.commanders_horn_attribute_name, R.string.commanders_horn_attribute_description, 0),
    HERO(R.string.hero_attribute_name, R.string.hero_attribute_description, R.drawable.bohater),
    MEDIC(R.string.medic_attribute_name, R.string.medic_attribute_description, R.drawable.medyk),
    SPY(R.string.spy_attribute_name, R.string.spy_attribute_description, R.drawable.zlodziej),
    SUPPLY(R.string.supply_attribute_name, R.string.supply_attribute_description, R.drawable.support),
    BOND(R.string.bond_attribute_name, R.string.bond_attribute_description, R.drawable.wiez),
    SCOURGE(R.string.scourge_attribute_name, R.string.scourge_attribute_description, R.drawable.pozoga),
}