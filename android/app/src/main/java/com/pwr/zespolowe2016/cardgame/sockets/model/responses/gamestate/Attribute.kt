package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.support.annotation.StringRes
import com.pwr.zespolowe2016.cardgame.R

enum class Attribute(@StringRes val attributeName: Int, @StringRes val attributeDescription: Int) {
    COLD(R.string.cold_attribute_name, R.string.cold_attribute_description),
    RAIN(R.string.rain_attribute_name, R.string.rain_attribute_description),
    FOG(R.string.fog_attribute_name, R.string.fog_attribute_description),
    GOOD_WEATHER(R.string.good_weather_attribute_name, R.string.good_weather_attribute_description),
    COMMANDERS_HORN(R.string.commanders_horn_attribute_name, R.string.commanders_horn_attribute_description),
    HERO(R.string.hero_attribute_name, R.string.hero_attribute_description),
    MEDIC(R.string.medic_attribute_name, R.string.medic_attribute_description),
    SPY(R.string.spy_attribute_name, R.string.spy_attribute_description),
    SUPPLY(R.string.supply_attribute_name, R.string.supply_attribute_description),
    BOND(R.string.bond_attribute_name, R.string.bond_attribute_description),
}