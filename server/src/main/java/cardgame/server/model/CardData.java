package cardgame.server.model;

import cardgame.model.Cards;
import cardgame.model.Row;

public class CardData {
    public String name;
    public int strength;

    public  CardData(Cards card, Row row) {
        name = card.toString();
        if (row != null) {
            strength = card.getCard().getStrength(row);
        } else {
            strength = card.getCard().basicStrength;
        }
    }
}
