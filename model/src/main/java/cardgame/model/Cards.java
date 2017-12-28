package cardgame.model;

import java.util.ArrayList;

public enum Cards {
    ARCHER(new Card(new ArrayList<>(), 5, null, null));

    private Card card;
    Cards(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
