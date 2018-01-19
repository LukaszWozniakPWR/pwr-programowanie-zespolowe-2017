package com.pwr.zespolowe2016.cardgame.game.cards

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.recyclerview.BaseViewHolder
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Attribute

class AttributeViewHolder(view: View) : BaseViewHolder<Attribute>(view) {

    val attributeName: TextView by bindView(R.id.attributeName)

    override fun displayItem(itemType: Attribute) {
        attributeName.setText(itemType.attributeName)
        attributeName.setOnClickListener {
            AlertDialog.Builder(attributeName.context)
                    .setTitle(itemType.attributeName)
                    .setMessage(itemType.attributeDescription)
                    .show()
        }
    }
}