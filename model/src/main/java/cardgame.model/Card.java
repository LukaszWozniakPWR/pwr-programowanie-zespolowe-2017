package cardgame.model;

import java.util.*;

public enum Card {
    Baldur(1, new ArrayList<>(Arrays.asList(Attribute.HERO, Attribute.MEDIC)), Collections.singletonList(1), 10, null, null),
    Snake_Rain(1, new ArrayList<>(), Collections.singletonList(3), 10, null, null),
    Loki(1, Collections.singletonList(Attribute.HERO), Collections.singletonList(1), 10, null, null),
    Hajmdal(1, Collections.singletonList(Attribute.HERO), Collections.singletonList(2), 10, null, null),
    Magni(3, Collections.singletonList(Attribute.HERO), Collections.singletonList(3), 10, null, null),
    Thrudheim_Archers(2, new ArrayList<>(), Collections.singletonList(2), 10, null, null),
    Aegir(1, Collections.singletonList(Attribute.SPY), Collections.singletonList(1), 9, null, null),
    Hodur(1, Collections.singletonList(Attribute.SPY), Collections.singletonList(1), 7, null, null),
    Frigg(1, new ArrayList<>(), Collections.singletonList(2), 6, null, null),
    Fulla(1, new ArrayList<>(), Collections.singletonList(2), 6, null, null),
    Widar(1, new ArrayList<>(), Collections.singletonList(1), 6, null, null),
    Forseti(1, new ArrayList<>(), Collections.singletonList(2), 5, null, null),
    Arrow_Rain(1, new ArrayList<>(), Collections.singletonList(3), 5, null, null),
    Young_Giant(2, Collections.singletonList(Attribute.BOND), Collections.singletonList(1), 5, null, BondClass.YOUNG_GIANT),
    Stone_Rain(1, new ArrayList<>(), Collections.singletonList(3), 5, null, null),
    Hermod(1, Collections.singletonList(Attribute.SPY), Collections.singletonList(1), 4, null, null),
    Kvaser(1, new ArrayList<>(), Collections.singletonList(2), 3, null, null),
    Sigyn(1, new ArrayList<>(), Collections.singletonList(1), 4, null, null),
    Freja(1, new ArrayList<>(), Collections.singletonList(2), 4, null, null),
    Hog_Horde(4, Collections.singletonList(Attribute.BOND), Collections.singletonList(1), 3, null, BondClass.HOG_HORDE),
    Broken_Catapult(1, new ArrayList<>(), Collections.singletonList(3), 3, null, null),
    Hel(1, new ArrayList<>(), Collections.singletonList(3), 3, null, null),
    Mimir(1, new ArrayList<>(), Collections.singletonList(3), 3, null, null),
    Axe_Launcher(2, Collections.singletonList(Attribute.BOND), Collections.singletonList(1), 2, null, BondClass.AXE_LAUNCHER),
    Sol(1, new ArrayList<>(), Collections.singletonList(2), 2, null, null),
    Mani(1, new ArrayList<>(), Collections.singletonList(2), 2, null, null),
    Gullweig(1, new ArrayList<>(), Collections.singletonList(1), 2, null, null),
    Wolf_Horde(2, Collections.singletonList(Attribute.MEDIC), Collections.singletonList(2), 1, null, null),
    Divine_Help(1, Collections.singletonList(Attribute.SUPPLY), Collections.singletonList(3), 0, null, null);



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

        if (attributes.contains(Attribute.BOND))
            for (Card c : row.elements)
                if (c != this && c.bondClass != null && c.bondClass == this.bondClass)
                    strength += weatherAffectedStrength;

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

    Card(int maxInDeck, List<Attribute> attributes, List<Integer> rowsAllowed, int basicStrength, MusterClass musterClass, BondClass bondClass) {
        this.maxInDeck = maxInDeck;
        this.attributes = attributes;
        this.rowsAllowed = rowsAllowed;
        this.basicStrength = basicStrength;
        this.musterClass = musterClass;
        this.bondClass = bondClass;
    }
}