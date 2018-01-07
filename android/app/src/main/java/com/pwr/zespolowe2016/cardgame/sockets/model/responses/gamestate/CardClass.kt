package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.R

enum class CardClass(
        val basePoints: Int,
        val rowInfo: RowInfo,
        val fraction: Fraction,
        @StringRes val cardName: Int,
        @StringRes val cardDescription: Int,
        @DrawableRes val cardImage: Int
) {
    /* NILFGAARD */
    @SerializedName("BALDUR") BALDUR(10, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.baldur_card_name, R.string.baldur_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("SNAKE_STORM") SNAKE_STORM(10, RowInfo.CATAPULTS, Fraction.NILFGAARD, R.string.snake_storm_card_name, R.string.snake_storm_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("LOKI") LOKI(10, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.loki_card_name, R.string.loki_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("HAJMDAL") HAJMDAL(10, RowInfo.ARCHERS, Fraction.NILFGAARD, R.string.hajmdal_card_name, R.string.hajmdal_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("MAGNI") MAGNI(10, RowInfo.CATAPULTS, Fraction.NILFGAARD, R.string.magni_card_name, R.string.magni_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("ARCHERS_THRUDHEIM") ARCHERS_THRUDHEIM(10, RowInfo.ARCHERS, Fraction.NILFGAARD, R.string.archers_thrudheim_card_name, R.string.archers_thrudheim_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("AEGIR") AEGIR(9, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.aegir_card_name, R.string.aegir_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("HODUR") HODUR(7, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.hodur_card_name, R.string.hodur_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("FRIGG") FRIGG(6, RowInfo.ARCHERS, Fraction.NILFGAARD, R.string.frigg_card_name, R.string.frigg_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("FULLA") FULLA(6, RowInfo.ARCHERS, Fraction.NILFGAARD, R.string.fulla_card_name, R.string.fulla_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("WIDAR") WIDAR(6, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.widar_card_name, R.string.widar_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("FORSETI") FORSETI(5, RowInfo.ARCHERS, Fraction.NILFGAARD, R.string.forseti_card_name, R.string.forseti_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("ARROW_STORM") ARROW_STORM(5, RowInfo.CATAPULTS, Fraction.NILFGAARD, R.string.arrow_storm_card_name, R.string.arrow_storm_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("YOUNG_GIANT") YOUNG_GIANT(5, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.young_giant_card_name, R.string.young_giant_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("ROCK_STORM") ROCK_STORM(5, RowInfo.CATAPULTS, Fraction.NILFGAARD, R.string.rock_storm_card_name, R.string.rock_storm_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("HERMOD") HERMOD(4, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.hermod_card_name, R.string.hermod_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("KVASER") KVASER(4, RowInfo.ARCHERS, Fraction.NILFGAARD, R.string.kvaser_card_name, R.string.kvaser_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("SIGYN") SIGYN(4, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.sigyn_card_name, R.string.sigyn_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("FREJA") FREJA(4, RowInfo.ARCHERS, Fraction.NILFGAARD, R.string.freja_card_name, R.string.freja_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("BOAR_HORDE") BOAR_HORDE(3, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.boar_horde_card_name, R.string.boar_horde_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("BATTERED_CATAPULTE") BATTERED_CATAPULTE(3, RowInfo.CATAPULTS, Fraction.NILFGAARD, R.string.battered_catapulte_card_name, R.string.battered_catapulte_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("HEL") HEL(3, RowInfo.CATAPULTS, Fraction.NILFGAARD, R.string.hel_card_name, R.string.hel_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("MIMIR") MIMIR(3, RowInfo.CATAPULTS, Fraction.NILFGAARD, R.string.mimir_card_name, R.string.mimir_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("AX_LAUNCHER") AX_LAUNCHER(2, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.ax_launcher_card_name, R.string.ax_launcher_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("SOL") SOL(2, RowInfo.ARCHERS, Fraction.NILFGAARD, R.string.sol_card_name, R.string.sol_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("MANI") MANI(2, RowInfo.ARCHERS, Fraction.NILFGAARD, R.string.mani_card_name, R.string.mani_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("GULLWEIG") GULLWEIG(2, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.gullweig_card_name, R.string.gullweig_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("WOLF_HORDE") WOLF_HORDE(1, RowInfo.ARCHERS, Fraction.NILFGAARD, R.string.wolf_horde_card_name, R.string.wolf_horde_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("SUPPORT_FROM_GODS") SUPPORT_FROM_GODS(0, RowInfo.CATAPULTS, Fraction.NILFGAARD, R.string.support_from_gods_card_name, R.string.support_from_gods_card_description, R.drawable.ic_dissatisfied_512),





}