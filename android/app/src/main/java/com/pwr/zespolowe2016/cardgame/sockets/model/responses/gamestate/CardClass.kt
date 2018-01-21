package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.R

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
    @SerializedName("BALDUR") BALDUR(10, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(Attribute.MEDIC, Attribute.HERO), R.string.baldur_card_name, R.string.baldur_card_description, R.drawable.baldur),
    @SerializedName("SNAKE_RAIN") SNAKE_STORM(10, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.snake_storm_card_name, R.string.snake_storm_card_description, R.drawable.deszczwunzy),
    @SerializedName("LOKI") LOKI(10, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(Attribute.HERO), R.string.loki_card_name, R.string.loki_card_description, R.drawable.loki),
    @SerializedName("HAJMDAL") HAJMDAL(10, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(Attribute.HERO), R.string.hajmdal_card_name, R.string.hajmdal_card_description, R.drawable.hajmdal),
    @SerializedName("MAGNI") MAGNI(10, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(Attribute.HERO), R.string.magni_card_name, R.string.magni_card_description, R.drawable.magni),
    @SerializedName("THRUDHEIM_ARCHERS") ARCHERS_THRUDHEIM(10, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.archers_thrudheim_card_name, R.string.archers_thrudheim_card_description, R.drawable.lucznicyztrudheim),
    @SerializedName("AEGIR") AEGIR(9, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(Attribute.SPY), R.string.aegir_card_name, R.string.aegir_card_description, R.drawable.aegir),
    @SerializedName("HODUR") HODUR(7, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(Attribute.SPY), R.string.hodur_card_name, R.string.hodur_card_description, R.drawable.hodur),
    @SerializedName("FRIGG") FRIGG(6, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.frigg_card_name, R.string.frigg_card_description, R.drawable.frigg),
    @SerializedName("FULLA") FULLA(6, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.fulla_card_name, R.string.fulla_card_description, R.drawable.fulla),
    @SerializedName("WIDAR") WIDAR(6, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.widar_card_name, R.string.widar_card_description, R.drawable.widar),
    @SerializedName("FORSETI") FORSETI(5, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.forseti_card_name, R.string.forseti_card_description, R.drawable.forseti),
    @SerializedName("ARROW_RAIN") ARROW_STORM(5, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.arrow_storm_card_name, R.string.arrow_storm_card_description, R.drawable.deszczstrzal),
    @SerializedName("YOUNG_GIANT") YOUNG_GIANT(5, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(Attribute.BOND), R.string.young_giant_card_name, R.string.young_giant_card_description, R.drawable.mlodyolbrzym),
    @SerializedName("STONE_RAIN") ROCK_STORM(5, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.rock_storm_card_name, R.string.rock_storm_card_description, R.drawable.deszczkamieni),
    @SerializedName("HERMOD") HERMOD(4, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(Attribute.SPY), R.string.hermod_card_name, R.string.hermod_card_description, R.drawable.hermod),
    @SerializedName("KVASER") KVASER(4, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.kvaser_card_name, R.string.kvaser_card_description, R.drawable.kvaser),
    @SerializedName("SIGYN") SIGYN(4, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.sigyn_card_name, R.string.sigyn_card_description, R.drawable.sigyn),
    @SerializedName("FREJA") FREJA(4, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.freja_card_name, R.string.freja_card_description, R.drawable.freja),
    @SerializedName("HOG_HORDE") BOAR_HORDE(3, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(Attribute.BOND), R.string.boar_horde_card_name, R.string.boar_horde_card_description, R.drawable.hordadzikow),
    @SerializedName("BROKEN_CATAPULT") BATTERED_CATAPULTE(3, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.battered_carapulte_card_name, R.string.battered_catapulte_card_description, R.drawable.zniszczonakatapulta),
    @SerializedName("HEL") HEL(3, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.hel_card_name, R.string.hel_card_description, R.drawable.hel),
    @SerializedName("MIMIR") MIMIR(3, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(), R.string.mimir_card_name, R.string.mimir_card_description, R.drawable.mimir),
    @SerializedName("AXE_LAUNCHER") AX_LAUNCHER(2, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(Attribute.BOND), R.string.ax_launcher_card_name, R.string.ax_launcher_card_description, R.drawable.wyrzutniatoporow),
    @SerializedName("SOL") SOL(2, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.sol_card_name, R.string.sol_card_description, R.drawable.sol),
    @SerializedName("MANI") MANI(2, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(), R.string.mani_card_name, R.string.mani_card_description, R.drawable.mani),
    @SerializedName("GULLWEIG") GULLWEIG(2, RowInfo.SWORDS, Fraction.NILFGAARD, listOf(), R.string.gullweig_card_name, R.string.gullweig_card_description, R.drawable.gullweig),
    @SerializedName("WOLF_HORDE") WOLF_HORDE(1, RowInfo.ARCHERS, Fraction.NILFGAARD, listOf(Attribute.MEDIC), R.string.wolf_horde_card_name, R.string.wolf_horde_card_description, R.drawable.hordawilkow),
    @SerializedName("DIVINE_HELP") SUPPORT_FROM_GODS(0, RowInfo.CATAPULTS, Fraction.NILFGAARD, listOf(Attribute.SUPPLY), R.string.support_from_gods_card_name, R.string.support_from_gods_card_description, R.drawable.wsparcie_od_bogow),

//    @SerializedName("ARCHER") ARCHER(5, RowInfo.ARCHERS, NILFGAARD, listOf(), R.string.nickname_hint, R.string.nickname_incorrect, R.drawable.ic_dissatisfied_512)

    /* NORTH */
    @SerializedName("MORRIGAN") MORRIGAN(10, RowInfo.CATAPULTS, Fraction.NORTH, listOf(), R.string.morrigan_card_name, R.string.morrigan_card_description, R.drawable.morrigan),
    @SerializedName("DAGDA") DAGDA(10, RowInfo.SWORDS, Fraction.NORTH, listOf(), R.string.dagda_card_name, R.string.dagda_card_description, R.drawable.dagda),
    @SerializedName("BELENUS") BELENUS(10, RowInfo.SWORDS, Fraction.NORTH, listOf(), R.string.belenus_card_name, R.string.belenus_card_description, R.drawable.belenus),
    @SerializedName("LUG") LUG(10, RowInfo.SWORDS, Fraction.NORTH, listOf(), R.string.lug_card_name, R.string.lug_card_description, R.drawable.lug),
    @SerializedName("CATAPULT") CATAPULT(8, RowInfo.CATAPULTS, Fraction.NORTH, listOf(Attribute.BOND), R.string.catapult_card_name, R.string.catapult_card_description, R.drawable.katapulta),
    @SerializedName("LUKAN") LUKAN(6, RowInfo.ARCHERS, Fraction.NORTH, listOf(), R.string.lukan_card_name, R.string.lukan_card_description, R.drawable.lukan),
    @SerializedName("BALLISTA") BALLISTA(6, RowInfo.CATAPULTS, Fraction.NORTH, listOf(), R.string.ballista_card_name, R.string.ballista_card_description, R.drawable.balista),
    @SerializedName("ICESTORM") ICESTORM(6, RowInfo.CATAPULTS, Fraction.NORTH, listOf(), R.string.icestorm_card_name, R.string.icestorm_description, R.drawable.gradobicie),
    @SerializedName("CAROUSEL") CAROUSEL(6, RowInfo.CATAPULTS, Fraction.NORTH, listOf(), R.string.carousel_card_name, R.string.carousel_description, R.drawable.hulajgrod),
    @SerializedName("JONSI") JONSI(5, RowInfo.CATAPULTS, Fraction.NORTH, listOf(Attribute.MEDIC), R.string.jonsi_card_name, R.string.jonsi_card_description, R.drawable.jonsi),
    //TODO Make pics for cards below
    @SerializedName("IBAR_DRUIDS") IBAR_DRUIDS(6, RowInfo.ARCHERS, Fraction.NORTH, listOf(Attribute.BOND), R.string.ibar_druids_card_name, R.string.ibar_druids_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("BRIGIT") BRIGIT(5, RowInfo.ARCHERS, Fraction.NORTH, listOf(), R.string.brigit_card_name, R.string.brigit_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("TARANIS") TARANIS(5, RowInfo.SWORDS, Fraction.NORTH, listOf(), R.string.taranis_card_name, R.string.taranis_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("ESUS") ESUS(5, RowInfo.SWORDS, Fraction.NORTH, listOf(), R.string.esus_card_name, R.string.esus_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("GALAHAD") GALAHAD(5, RowInfo.SWORDS, Fraction.NORTH, listOf(Attribute.SPY), R.string.galahad_card_name, R.string.galahad_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("AIBELL") AIBELL(5, RowInfo.ARCHERS, Fraction.NORTH, listOf(), R.string.aibell_card_name, R.string.aibell_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("UTHER") UTHER(4, RowInfo.ARCHERS, Fraction.NORTH, listOf(), R.string.uther_card_name, R.string.uther_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("TALITIA") TALITIA(4, RowInfo.ARCHERS, Fraction.NORTH, listOf(), R.string.talitia_card_name, R.string.talitia_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("ARTHWYS") ARTHWYS(4, RowInfo.SWORDS, Fraction.NORTH, listOf(Attribute.SPY), R.string.arthwys_card_name, R.string.arthwys_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("GRAY_LINES") GRAY_LINES(4, RowInfo.SWORDS, Fraction.NORTH, listOf(Attribute.BOND), R.string.gray_lines_card_name, R.string.gray_lines_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("GWYNEDD") GWYNEDD(2, RowInfo.SWORDS, Fraction.NORTH, listOf(), R.string.gwynedd_card_name, R.string.gwynedd_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("OSTENDA_INFANTRY") OSTENDA_INFANTRY(1, RowInfo.SWORDS, Fraction.NORTH, listOf(), R.string.ostenda_infantry_card_name, R.string.ostenda_infantry_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("ARMED_PEASANTS") ARMED_PEASANTS(1, RowInfo.SWORDS, Fraction.NORTH, listOf(Attribute.BOND), R.string.armed_peasants_card_name, R.string.armed_peasants_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("POWYS") POWYS(1, RowInfo.CATAPULTS, Fraction.NORTH, listOf(Attribute.SUPPLY), R.string.powys_launcher_card_name, R.string.powys_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("OWAIN") OWAIN(1, RowInfo.CATAPULTS, Fraction.NORTH, listOf(Attribute.SPY), R.string.owain_card_name, R.string.owain_card_description, R.drawable.ic_dissatisfied_512),

    /* NONE */
    //TODO zmienić punkty i rzędy dla poniższych kar
    @SerializedName("COMMANDERS_HORN") COMMANDERS_HORN(-1, RowInfo.ANY_OF_YOURS, Fraction.NONE, listOf(Attribute.COMMANDERS_HORN), R.string.commanders_horn_attribute_name, R.string.commanders_horn_attribute_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("SCOURGE") SCOURGE(-1, RowInfo.EMPTY, Fraction.NONE, listOf(Attribute.SCOURGE), R.string.scourge_attribute_name, R.string.scourge_attribute_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("COLD") COLD(-1, RowInfo.EMPTY, Fraction.NONE, listOf(Attribute.COLD), R.string.cold_attribute_name, R.string.cold_attribute_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("RAIN") RAIN(-1, RowInfo.EMPTY, Fraction.NONE, listOf(Attribute.RAIN), R.string.rain_attribute_name, R.string.rain_attribute_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("FOG") FOG(-1, RowInfo.EMPTY, Fraction.NONE, listOf(Attribute.FOG), R.string.fog_attribute_name, R.string.fog_attribute_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("GOOD_WEATHER") GOOD_WEATHER(-1, RowInfo.EMPTY, Fraction.NONE, listOf(Attribute.GOOD_WEATHER), R.string.good_weather_attribute_name, R.string.good_weather_attribute_description, R.drawable.ic_dissatisfied_512),
}
