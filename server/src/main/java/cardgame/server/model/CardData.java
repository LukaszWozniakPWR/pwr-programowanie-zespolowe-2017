package cardgame.server.model;

import cardgame.model.Card;
import cardgame.model.Row;

public class CardData {
    public String name;
    public int strength;

    public  CardData(Card card, Row row) {
        name = card.toString().toUpperCase();
        if (row != null) {
            strength = card.getStrength(row);
        } else {
            strength = card.basicStrength;
        }
    }
}
