package com.pwr.zespolowe2016.cardgame.game.cards

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.pwr.zespolowe2016.cardgame.R

class CardsBottomSheetFragment() : BottomSheetDialogFragment(), DialogInterface.OnShowListener {

    private lateinit var behavior: BottomSheetBehavior<*>
    private lateinit var cardsAdapter: HorizontalCardsAdapter
    private lateinit var recyclerView: RecyclerView

    private var positionToScroll = 0

    var onCardClickListener: (Card) -> Unit = { /* NO-OP */ }
        set(value) {
            cardsAdapter.onItemClickListener = value
            field = value
        }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.bottom_sheet_fragment, null)
        recyclerView = contentView.findViewById(R.id.bottom_sheet_list) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = cardsAdapter
        if (positionToScroll < cardsAdapter.itemCount) {
            recyclerView.scrollToPosition(positionToScroll)
        }
        configureDialog(dialog, contentView)
        configureBehavior(contentView)
    }

    private fun configureDialog(dialog: Dialog, contentView: View) {
        dialog.setContentView(contentView)
        dialog.setOnShowListener(this)
    }

    private fun configureBehavior(view: View) {
        val layoutParams = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        behavior = layoutParams.behavior as BottomSheetBehavior<*>
        behavior.peekHeight = 400
        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    dismiss()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }

    fun loadCards(context: Context, cards: List<Card>) {
        cardsAdapter = HorizontalCardsAdapter(context)
        cardsAdapter.setData(cards)
    }

    fun scrollToPosition(position: Int) {
        positionToScroll = position
    }

    override fun onShow(dialog: DialogInterface?) {
        if (positionToScroll < cardsAdapter.itemCount) {
            recyclerView.scrollToPosition(positionToScroll)
        }
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}