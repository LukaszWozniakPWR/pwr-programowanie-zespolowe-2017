package cardgame.model;

import java.util.*;

public enum Card {

    Commanders_Horn(0, 3, Collections.singletonList(Attribute.COMMANDERS_HORN), Arrays.asList(1, 2, 3), 0, null, null),
    Scourge(0, 3, Collections.singletonList(Attribute.SCOURGE), Collections.singletonList(0), 0, null, null),
    Cold(0, 3, Collections.singletonList(Attribute.COLD), Collections.singletonList(0), 0, null, null),
    Rain(0, 3, Collections.singletonList(Attribute.RAIN), Collections.singletonList(0), 0, null, null),
    Fog(0, 3, Collections.singletonList(Attribute.FOG), Collections.singletonList(0), 0, null, null),
    Good_Weather(0, 3, Collections.singletonList(Attribute.GOOD_WEATHER), Collections.singletonList(0), 0, null, null),

    Morrigan(1, 1, Collections.singletonList(Attribute.HERO), Collections.singletonList(3), 10, null, null),
    Dagda(1, 1, Collections.singletonList(Attribute.HERO), Collections.singletonList(1), 10, null, null),
    Belenius(1, 1, Collections.singletonList(Attribute.HERO), Collections.singletonList(1), 10, null, null),
    Lug(1, 1, Collections.singletonList(Attribute.HERO), Collections.singletonList(1), 10, null, null),
    Catapult(1, 2, Collections.singletonList(Attribute.BOND), Collections.singletonList(3), 8, null, BondClass.CATAPULT),
    Lukan(1, 1, new ArrayList<>(), Collections.singletonList(2), 6, null, null),
    Ballista(1, 1, new ArrayList<>(), Collections.singletonList(3), 6, null, null),
    Icestorm(1, 2, new ArrayList<>(), Collections.singletonList(3), 6, null, null),
    Carousel(1, 1, new ArrayList<>(), Collections.singletonList(3), 6, null, null),
    Jonsi(1, 1, Collections.singletonList(Attribute.MEDIC), Collections.singletonList(3), 5, null, null),
    Ibar_Druids(1, 3, Collections.singletonList(Attribute.BOND), Collections.singletonList(2), 5, null, BondClass.IBAR_DRUIDS),
    Brigit(1, 1, new ArrayList<>(), Collections.singletonList(2), 5, null, null),
    Taranis(1, 1, new ArrayList<>(), Collections.singletonList(1), 5, null, null),
    Esus(1, 1, new ArrayList<>(), Collections.singletonList(1), 5, null, null),
    Galahad(1, 1, Collections.singletonList(Attribute.SPY), Collections.singletonList(1), 5, null, null),
    Aibell(1, 1, new ArrayList<>(), Collections.singletonList(2), 5, null, null),
    Uther(1, 1, new ArrayList<>(), Collections.singletonList(2), 4, null, null),
    Talitia(1, 1, new ArrayList<>(), Collections.singletonList(2), 4, null, null),
    Arthwys(1, 1, Collections.singletonList(Attribute.SPY), Collections.singletonList(1), 4, null, null),
    Gray_Lines(1, 3, Collections.singletonList(Attribute.BOND), Collections.singletonList(1), 4, null, BondClass.GRAY_LINES),
    Gwynedd(1, 1, new ArrayList<>(), Collections.singletonList(1), 2, null, null),
    Ostenda_Infantry(1, 2, new ArrayList<>(), Collections.singletonList(1), 2, null, null),
    Armed_Peasants(1, 3, Collections.singletonList(Attribute.BOND), Collections.singletonList(1), 1, null, BondClass.ARMED_PEASANTS),
    Powys(1, 1, Collections.singletonList(Attribute.SUPPLY), Collections.singletonList(3), 1, null, null),
    Owain(1, 1, Collections.singletonList(Attribute.SPY), Collections.singletonList(3), 1, null, null),

    Baldur(2, 1, Arrays.asList(Attribute.HERO, Attribute.MEDIC), Collections.singletonList(1), 10, null, null),
    Snake_Rain(2, 1, new ArrayList<>(), Collections.singletonList(3), 10, null, null),
    Loki(2, 1, Collections.singletonList(Attribute.HERO), Collections.singletonList(1), 10, null, null),
    Hajmdal(2, 1, Collections.singletonList(Attribute.HERO), Collections.singletonList(2), 10, null, null),
    Magni(2, 3, Collections.singletonList(Attribute.HERO), Collections.singletonList(3), 10, null, null),
    Thrudheim_Archers(2, 2, new ArrayList<>(), Collections.singletonList(2), 10, null, null),
    Aegir(2, 1, Collections.singletonList(Attribute.SPY), Collections.singletonList(1), 9, null, null),
    Hodur(2, 1, Collections.singletonList(Attribute.SPY), Collections.singletonList(1), 7, null, null),
    Frigg(2, 1, new ArrayList<>(), Collections.singletonList(2), 6, null, null),
    Fulla(2, 1, new ArrayList<>(), Collections.singletonList(2), 6, null, null),
    Widar(2, 1, new ArrayList<>(), Collections.singletonList(1), 6, null, null),
    Forseti(2, 1, new ArrayList<>(), Collections.singletonList(2), 5, null, null),
    Arrow_Rain(2, 1, new ArrayList<>(), Collections.singletonList(3), 5, null, null),
    Young_Giant(2, 2, Collections.singletonList(Attribute.BOND), Collections.singletonList(1), 5, null, BondClass.YOUNG_GIANT),
    Stone_Rain(2, 1, new ArrayList<>(), Collections.singletonList(3), 5, null, null),
    Hermod(2, 1, Collections.singletonList(Attribute.SPY), Collections.singletonList(1), 4, null, null),
    Kvaser(2, 1, new ArrayList<>(), Collections.singletonList(2), 3, null, null),
    Sigyn(2, 1, new ArrayList<>(), Collections.singletonList(1), 4, null, null),
    Freja(2, 1, new ArrayList<>(), Collections.singletonList(2), 4, null, null),
    Hog_Horde(2, 4, Collections.singletonList(Attribute.BOND), Collections.singletonList(1), 3, null, BondClass.HOG_HORDE),
    Broken_Catapult(2, 1, new ArrayList<>(), Collections.singletonList(3), 3, null, null),
    Hel(2, 1, new ArrayList<>(), Collections.singletonList(3), 3, null, null),
    Mimir(2, 1, new ArrayList<>(), Collections.singletonList(3), 3, null, null),
    Axe_Launcher(2, 3, Collections.singletonList(Attribute.BOND), Collections.singletonList(3), 3, null, BondClass.AXE_LAUNCHER),
    Sol(2, 1, new ArrayList<>(), Collections.singletonList(2), 2, null, null),
    Mani(2, 1, new ArrayList<>(), Collections.singletonList(2), 2, null, null),
    Gullweig(2, 1, new ArrayList<>(), Collections.singletonList(1), 2, null, null),
    Wolf_Horde(2, 2, Collections.singletonList(Attribute.MEDIC), Collections.singletonList(2), 1, null, null),
    Divine_Help(2, 1, Collections.singletonList(Attribute.SUPPLY), Collections.singletonList(3), 0, null, null);


    public int deck;
    public int maxInDeck;
    public List<Attribute> attributes;
    public List<Integer> rowsAllowed;
    public int basicStrength;
    public MusterClass musterClass;
    private BondClass bondClass;

    public int getStrength(Row row) {
        int strength = basicStrength;

        if (attributes.contains(Attribute.HERO))
            return strength;

        int weatherAffectedStrength = basicStrength;
        if (row.isAffectedBy(Effect.BAD_WEATHER))
            if (basicStrength >= 1)
                weatherAffectedStrength = strength = 1;
            else
                weatherAffectedStrength = strength = 0;

        if (attributes.contains(Attribute.BOND)) {
            for (Card c : row.elements)
                if (c.bondClass == this.bondClass) // Card is enum, we can't simply use c != this
                    strength += weatherAffectedStrength;
	    strength -= weatherAffectedStrength; // reverting this being counted

	}

        if (row.hasHorn())
            strength *= 2;

        for (Card c : row.elements)
            if (c != this && c.is(Attribute.SUPPLY))
                ++strength;

        return strength;
    }

    public void specialActions(Player player, int row) throws Game.InvalidMove {
        for (Attribute a : attributes)
            a.specialAction(player.game, row);
    }

    public boolean is(Attribute a) {
        return attributes.contains(a);
    }

    private static List<Card> VALUES = new ArrayList<>();
    static {
        for (Card c : values()) {
            for (int i = 0; i < c.maxInDeck; ++i) {
                VALUES.add(c);
            }
        }
    }
    private static Random RANDOM = new Random();
    public static Card getRandom() {
        return VALUES.get(RANDOM.nextInt(VALUES.size()));
    }

    Card(int deck, int maxInDeck, List<Attribute> attributes, List<Integer> rowsAllowed, int basicStrength, MusterClass musterClass, BondClass bondClass) {
        this.deck = deck;
        this.maxInDeck = maxInDeck;
        this.attributes = attributes;
        this.rowsAllowed = rowsAllowed;
        this.basicStrength = basicStrength;
        this.musterClass = musterClass;
        this.bondClass = bondClass;
    }
}
