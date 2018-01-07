package cardgame.model;

import java.util.Comparator;

public class StandardCardComparator implements Comparator<Card> {

    @Override
    public int compare(Card c1, Card c2) {
        return Integer.compare(c1.basicStrength, c2.basicStrength);
    }
}
