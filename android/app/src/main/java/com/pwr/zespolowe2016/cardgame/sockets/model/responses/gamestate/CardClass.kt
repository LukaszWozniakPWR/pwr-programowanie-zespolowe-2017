package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Fraction.NILFGAARD

enum class CardClass(
        val basePoints: Int,
        val rowInfo: RowInfo,
        val fraction: Fraction,
        val attributes: List<Attribute>,
        @StringRes val cardName: Int,
        @StringRes val cardDescription: Int,
        @DrawableRes val cardImage: Int
) {
    /* NILFGAARD */
    @SerializedName("Baldur") BALDUR(10, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.baldur_card_name, R.string.baldur_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Snake_Rain") SNAKE_STORM(10, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.snake_storm_card_name, R.string.snake_storm_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Loki") LOKI(10, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.loki_card_name, R.string.loki_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Hajmdal") HAJMDAL(10, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.hajmdal_card_name, R.string.hajmdal_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Magni") MAGNI(10, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.magni_card_name, R.string.magni_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Thrudheim_Archers") ARCHERS_THRUDHEIM(10, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.archers_thrudheim_card_name, R.string.archers_thrudheim_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Aegir") AEGIR(9, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.aegir_card_name, R.string.aegir_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Hodur") HODUR(7, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.hodur_card_name, R.string.hodur_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Frigg") FRIGG(6, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.frigg_card_name, R.string.frigg_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Fulla") FULLA(6, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.fulla_card_name, R.string.fulla_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Widar") WIDAR(6, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.widar_card_name, R.string.widar_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Forseti") FORSETI(5, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.forseti_card_name, R.string.forseti_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Arrow_Rain") ARROW_STORM(5, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.arrow_storm_card_name, R.string.arrow_storm_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Young_Giant") YOUNG_GIANT(5, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.young_giant_card_name, R.string.young_giant_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Stone_Rain") ROCK_STORM(5, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.rock_storm_card_name, R.string.rock_storm_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Hermod") HERMOD(4, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.hermod_card_name, R.string.hermod_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Kvaser") KVASER(4, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.kvaser_card_name, R.string.kvaser_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Sigyn") SIGYN(4, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.sigyn_card_name, R.string.sigyn_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Freja") FREJA(4, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.freja_card_name, R.string.freja_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Hog_Horde") BOAR_HORDE(3, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.boar_horde_card_name, R.string.boar_horde_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Broken_Catapult") BATTERED_CATAPULTE(3, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.battered_carapulte_card_name, R.string.battered_catapulte_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Hel") HEL(3, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.hel_card_name, R.string.hel_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Mimir") MIMIR(3, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.mimir_card_name, R.string.mimir_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Axe_Launcher") AX_LAUNCHER(2, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.ax_launcher_card_name, R.string.ax_launcher_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Sol") SOL(2, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.sol_card_name, R.string.sol_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Mani") MANI(2, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.mani_card_name, R.string.mani_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Gullweig") GULLWEIG(2, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.gullweig_card_name, R.string.gullweig_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Wolf_Horde") WOLF_HORDE(1, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.wolf_horde_card_name, R.string.wolf_horde_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("Divine_Help") SUPPORT_FROM_GODS(0, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.support_from_gods_card_name, R.string.support_from_gods_card_description, R.drawable.ic_dissatisfied_512),

    @SerializedName("ARCHER") ARCHER(5, RowInfo.ARCHERS, NILFGAARD, listOf(), R.string.nickname_hint, R.string.nickname_incorrect, R.drawable.ic_dissatisfied_512)
}