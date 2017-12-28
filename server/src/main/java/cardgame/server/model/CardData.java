package cardgame.server.model;

import cardgame.model.Card;
import cardgame.model.Row;

public class CardData {
    public String name;
    public int strength;

    public CardData(Card card, Row row) {
        name = card.getClass().getSimpleName();
        strength = card.getStrength(row);
    }
}
