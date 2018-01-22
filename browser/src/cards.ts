import assets from "./assets.js";
import Attribute from "./model/Attribute";
import Fraction from "./model/Fraction";
import RowInfo from "./model/RowInfo";
import R from "./R";

export default {
    BALDUR: {
        name: R.baldur_card_name,
        description: R.baldur_card_description,
        img: assets["../assets/cards/Baldur.png"],
        basicStrength: 10,
        row: RowInfo.SWORDS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.MEDIC, Attribute.HERO],
    },

    SNAKE_RAIN: {
        name: R.snake_storm_card_name,
        description: R.snake_storm_card_description,
        img: assets["../assets/cards/deszczwunzy.png"],
        basicStrength: 10,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    LOKI: {
        name: R.loki_card_name,
        description: R.loki_card_description,
        img: assets["../assets/cards/loki.png"],
        basicStrength: 10,
        row: RowInfo.SWORDS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.HERO],
    },

    HAJMDAL: {
        name: R.hajmdal_card_name,
        description: R.hajmdal_card_description,
        img: assets["../assets/cards/hajmdal.png"],
        basicStrength: 10,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.HERO],
    },

    MAGNI: {
        name: R.magni_card_name,
        description: R.magni_card_description,
        img: assets["../assets/cards/Magni.png"],
        basicStrength: 10,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.HERO],
    },

    THRUDHEIM_ARCHERS: {
        name: R.archers_thrudheim_card_name,
        description: R.archers_thrudheim_card_description,
        img: assets["../assets/cards/lucznicyztrudheim.png"],
        basicStrength: 10,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    AEGIR: {
        name: R.aegir_card_name,
        description: R.aegir_card_description,
        img: assets["../assets/cards/aegir.png"],
        basicStrength: 9,
        row: RowInfo.SWORDS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.SPY],
    },

    HODUR: {
        name: R.hodur_card_name,
        description: R.hodur_card_description,
        img: assets["../assets/cards/hodur.png"],
        basicStrength: 7,
        row: RowInfo.SWORDS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.SPY],
    },

    FRIGG: {
        name: R.frigg_card_name,
        description: R.frigg_card_description,
        img: assets["../assets/cards/frigg.png"],
        basicStrength: 6,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    FULLA: {
        name: R.fulla_card_name,
        description: R.fulla_card_description,
        img: assets["../assets/cards/fulla.png"],
        basicStrength: 6,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    WIDAR: {
        name: R.widar_card_name,
        description: R.widar_card_description,
        img: assets["../assets/cards/widar.png"],
        basicStrength: 6,
        row: RowInfo.SWORDS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    FORSETI: {
        name: R.forseti_card_name,
        description: R.forseti_card_description,
        img: assets["../assets/cards/forseti.png"],
        basicStrength: 5,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    ARROW_RAIN: {
        name: R.arrow_storm_card_name,
        description: R.arrow_storm_card_description,
        img: assets["../assets/cards/deszczstrzal.png"],
        basicStrength: 5,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    YOUNG_GIANT: {
        name: R.young_giant_card_name,
        description: R.young_giant_card_description,
        img: assets["../assets/cards/mlodyolbrzym.png"],
        basicStrength: 5,
        row: RowInfo.SWORDS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.BOND],
    },

    STONE_RAIN: {
        name: R.rock_storm_card_name,
        description: R.rock_storm_card_description,
        img: assets["../assets/cards/deszczkamieni.png"],
        basicStrength: 5,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    HERMOD: {
        name: R.hermod_card_name,
        description: R.hermod_card_description,
        img: assets["../assets/cards/hermod.png"],
        basicStrength: 4,
        row: RowInfo.SWORDS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.SPY],
    },

    KVASER: {
        name: R.kvaser_card_name,
        description: R.kvaser_card_description,
        img: assets["../assets/cards/kvaser.png"],
        basicStrength: 4,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    SIGYN: {
        name: R.sigyn_card_name,
        description: R.sigyn_card_description,
        img: assets["../assets/cards/sigyn.png"],
        basicStrength: 4,
        row: RowInfo.SWORDS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    FREJA: {
        name: R.freja_card_name,
        description: R.freja_card_description,
        img: assets["../assets/cards/freja.png"],
        basicStrength: 4,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    HOG_HORDE: {
        name: R.boar_horde_card_name,
        description: R.boar_horde_card_description,
        img: assets["../assets/cards/hordadzikow.png"],
        basicStrength: 3,
        row: RowInfo.SWORDS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.BOND],
    },

    BROKEN_CATAPULT: {
        name: R.battered_carapulte_card_name,
        description: R.battered_catapulte_card_description,
        img: assets["../assets/cards/zniszczonakatapulta.png"],
        basicStrength: 3,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    HEL: {
        name: R.hel_card_name,
        description: R.hel_card_description,
        img: assets["../assets/cards/hel.png"],
        basicStrength: 3,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    MIMIR: {
        name: R.mimir_card_name,
        description: R.mimir_card_description,
        img: assets["../assets/cards/mimir.png"],
        basicStrength: 3,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    AXE_LAUNCHER: {
        name: R.ax_launcher_card_name,
        description: R.ax_launcher_card_description,
        img: assets["../assets/cards/wyrzutniatoporow.png"],
        basicStrength: 2,
        row: RowInfo.SWORDS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.BOND],
    },

    SOL: {
        name: R.sol_card_name,
        description: R.sol_card_description,
        img: assets["../assets/cards/sol.png"],
        basicStrength: 2,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    MANI: {
        name: R.mani_card_name,
        description: R.mani_card_description,
        img: assets["../assets/cards/mani.png"],
        basicStrength: 2,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    GULLWEIG: {
        name: R.gullweig_card_name,
        description: R.gullweig_card_description,
        img: assets["../assets/cards/gullweig.png"],
        basicStrength: 2,
        row: RowInfo.SWORDS,
        fraction: Fraction.NILFGAARD,
        attributes: [],
    },

    WOLF_HORDE: {
        name: R.wolf_horde_card_name,
        description: R.wolf_horde_card_description,
        img: assets["../assets/cards/hordawilkow.png"],
        basicStrength: 1,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.MEDIC],
    },

    DIVINE_HELP: {
        name: R.support_from_gods_card_name,
        description: R.support_from_gods_card_description,
        img: assets["../assets/cards/wsparcie odbogow.png"],
        basicStrength: 0,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NILFGAARD,
        attributes: [Attribute.SUPPLY],
    },

    MORRIGAN: {
        name: R.morrigan_card_name,
        description: R.morrigan_card_description,
        img: assets["../assets/cards/morrigan.png"],
        basicStrength: 10,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    DAGDA: {
        name: R.dagda_card_name,
        description: R.dagda_card_description,
        img: assets["../assets/cards/Dagda.png"],
        basicStrength: 10,
        row: RowInfo.SWORDS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    BELENUS: {
        name: R.belenus_card_name,
        description: R.belenus_card_description,
        img: assets["../assets/cards/belenus.png"],
        basicStrength: 10,
        row: RowInfo.SWORDS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    LUG: {
        name: R.lug_card_name,
        description: R.lug_card_description,
        img: assets["../assets/cards/lug.png"],
        basicStrength: 10,
        row: RowInfo.SWORDS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    CATAPULT: {
        name: R.catapult_card_name,
        description: R.catapult_card_description,
        img: assets["../assets/cards/katapulta.png"],
        basicStrength: 8,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NORTH,
        attributes: [Attribute.BOND],
    },

    LUKAN: {
        name: R.lukan_card_name,
        description: R.lukan_card_description,
        img: assets["../assets/cards/lukan.png"],
        basicStrength: 6,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    BALLISTA: {
        name: R.ballista_card_name,
        description: R.ballista_card_description,
        img: assets["../assets/cards/balista.png"],
        basicStrength: 6,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    ICESTORM: {
        name: R.icestorm_card_name,
        description: R.icestorm_description,
        img: assets["../assets/cards/gradobicie.png"],
        basicStrength: 6,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    CAROUSEL: {
        name: R.carousel_card_name,
        description: R.carousel_description,
        img: assets["../assets/cards/hulajgrod.png"],
        basicStrength: 6,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    JONSI: {
        name: R.jonsi_card_name,
        description: R.jonsi_card_description,
        img: assets["../assets/cards/jonsi.png"],
        basicStrength: 5,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NORTH,
        attributes: [Attribute.MEDIC],
    },

    IBAR_DRUIDS: {
        name: R.ibar_druids_card_name,
        description: R.ibar_druids_card_description,
        img: assets["../assets/cards/druidzizibar.png"],
        basicStrength: 6,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NORTH,
        attributes: [Attribute.BOND],
    },

    BRIGIT: {
        name: R.brigit_card_name,
        description: R.brigit_card_description,
        img: assets["../assets/cards/brigit.png"],
        basicStrength: 5,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    TARANIS: {
        name: R.taranis_card_name,
        description: R.taranis_card_description,
        img: assets["../assets/cards/taranis.png"],
        basicStrength: 5,
        row: RowInfo.SWORDS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    ESUS: {
        name: R.esus_card_name,
        description: R.esus_card_description,
        img: assets["../assets/cards/esus.png"],
        basicStrength: 5,
        row: RowInfo.SWORDS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    GALAHAD: {
        name: R.galahad_card_name,
        description: R.galahad_card_description,
        img: assets["../assets/cards/galahad.png"],
        basicStrength: 5,
        row: RowInfo.SWORDS,
        fraction: Fraction.NORTH,
        attributes: [Attribute.SPY],
    },

    AIBELL: {
        name: R.aibell_card_name,
        description: R.aibell_card_description,
        img: assets["../assets/cards/aibell.png"],
        basicStrength: 5,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    UTHER: {
        name: R.uther_card_name,
        description: R.uther_card_description,
        img: assets["../assets/cards/uther.png"],
        basicStrength: 4,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    TALITIA: {
        name: R.talitia_card_name,
        description: R.talitia_card_description,
        img: assets["../assets/cards/talitia.png"],
        basicStrength: 4,
        row: RowInfo.ARCHERS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    ARTHWYS: {
        name: R.arthwys_card_name,
        description: R.arthwys_card_description,
        img: assets["../assets/cards/arthwys.png"],
        basicStrength: 4,
        row: RowInfo.SWORDS,
        fraction: Fraction.NORTH,
        attributes: [Attribute.SPY],
    },

    GRAY_LINES: {
        name: R.gray_lines_card_name,
        description: R.gray_lines_card_description,
        img: assets["../assets/cards/szareszeregi.png"],
        basicStrength: 4,
        row: RowInfo.SWORDS,
        fraction: Fraction.NORTH,
        attributes: [Attribute.BOND],
    },

    GWYNEDD: {
        name: R.gwynedd_card_name,
        description: R.gwynedd_card_description,
        img: assets["../assets/cards/gwynedd.png"],
        basicStrength: 2,
        row: RowInfo.SWORDS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    OSTENDA_INFANTRY: {
        name: R.ostenda_infantry_card_name,
        description: R.ostenda_infantry_description,
        img: assets["../assets/cards/piechurzyzostendy.png"],
        basicStrength: 1,
        row: RowInfo.SWORDS,
        fraction: Fraction.NORTH,
        attributes: [],
    },

    ARMED_PEASANTS: {
        name: R.armed_peasants_card_name,
        description: R.armed_peasants_card_description,
        img: assets["../assets/cards/uzbrojeniwiesniacy.png"],
        basicStrength: 1,
        row: RowInfo.SWORDS,
        fraction: Fraction.NORTH,
        attributes: [Attribute.BOND],
    },

    POWYS: {
        name: R.powys_launcher_card_name,
        description: R.powys_card_description,
        img: assets["../assets/cards/powys.png"],
        basicStrength: 1,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NORTH,
        attributes: [Attribute.SUPPLY],
    },

    OWAIN: {
        name: R.owain_card_name,
        description: R.owain_card_description,
        img: assets["../assets/cards/Owain.png"],
        basicStrength: 1,
        row: RowInfo.CATAPULTS,
        fraction: Fraction.NORTH,
        attributes: [Attribute.SPY],
    },

    COMMANDERS_HORN: {
        name: R.commanders_horn_attribute_name,
        description: R.commanders_horn_attribute_description,
        img: assets["../assets/cards/róg bojowy.png"],
        basicStrength: 0,
        row: RowInfo.ANY_OF_YOURS,
        fraction: Fraction.NONE,
        attributes: [Attribute.COMMANDERS_HORN],
    },

    SCOURGE: {
        name: R.scourge_attribute_name,
        description: R.scourge_attribute_description,
        img: assets["../assets/cards/pozoga.png"],
        basicStrength: 0,
        row: RowInfo.EMPTY,
        fraction: Fraction.NONE,
        attributes: [Attribute.SCOURGE],
    },

    COLD: {
        name: R.cold_attribute_name,
        description: R.cold_attribute_description,
        img: assets["../assets/cards/mroz.png"],
        basicStrength: 0,
        row: RowInfo.EMPTY,
        fraction: Fraction.NONE,
        attributes: [Attribute.COLD],
    },

    RAIN: {
        name: R.rain_attribute_name,
        description: R.rain_attribute_description,
        img: assets["../assets/cards/deszcz.png"],
        basicStrength: 0,
        row: RowInfo.EMPTY,
        fraction: Fraction.NONE,
        attributes: [Attribute.RAIN],
    },

    FOG: {
        name: R.fog_attribute_name,
        description: R.fog_attribute_description,
        img: assets["../assets/cards/mgla.png"],
        basicStrength: 0,
        row: RowInfo.EMPTY,
        fraction: Fraction.NONE,
        attributes: [Attribute.FOG],
    },

    GOOD_WEATHER: {
        name: R.good_weather_attribute_name,
        description: R.good_weather_attribute_description,
        img: assets["../assets/cards/dobra pogoda.png"],
        basicStrength: 0,
        row: RowInfo.EMPTY,
        fraction: Fraction.NONE,
        attributes: [Attribute.GOOD_WEATHER],
    },
};
