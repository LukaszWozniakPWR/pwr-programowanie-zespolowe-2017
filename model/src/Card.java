public class Card {
    public CardType cardType;
    public int basicStrength;

    public int getStrength(Row row) {
        int strength = basicStrength;

        if (cardType.is(Attribute.HERO))
            return strength;

        int weatherAffectedStrength = basicStrength;
        if (row.isAffectedBy(Effect.BAD_WEATHER))
            weatherAffectedStrength = strength = 1;

        if (cardType.is(Attribute.BOND))
            for (Card c : row.elements)
                if (c != this && c.cardType == this.cardType)
                    strength += weatherAffectedStrength;

        for (Card c : row.elements)
            if (c.cardType.is(Attribute.SUPPLY))
                ++strength;

        if (row.hasHorn())
            strength *= 2;

        return strength;
    }

    public void specialAction(Player player, int row) {
        cardType.specialAction(player, row);
    }
}
