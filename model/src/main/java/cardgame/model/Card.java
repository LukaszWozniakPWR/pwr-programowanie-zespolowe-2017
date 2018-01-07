package main.java.cardgame.model;

import java.util.*;

public enum Card {
    ARCHER(new ArrayList<>(), Collections.singletonList(2), 5, null, null);

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

    public void specialActions(Player player, int row) {
        for (Attribute a : attributes)
            a.specialAction(player, row);
    }

    public boolean is(Attribute a) {
        return attributes.contains(a);
    }

    private static final Card[] VALUES = values();
    private static Random RANDOM = new Random();
    public static Card getRandom() {
        return VALUES[RANDOM.nextInt(VALUES.length)];
    }

    Card(List<Attribute> attributes, List<Integer> rowsAllowed, int basicStrength, MusterClass musterClass, BondClass bondClass) {
        this.attributes = attributes;
        this.rowsAllowed = rowsAllowed;
        this.basicStrength = basicStrength;
        this.musterClass = musterClass;
        this.bondClass = bondClass;
    }
}