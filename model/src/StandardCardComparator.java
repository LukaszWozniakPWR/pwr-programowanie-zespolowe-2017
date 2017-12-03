import java.util.Comparator;

public class StandardCardComparator implements Comparator<Card> {

    @Override
    public int compare(Card c1, Card c2) {
        if (c1.basicStrength < c2.basicStrength) {
            return -1;
        } else if (c1.basicStrength > c2.basicStrength ) {
            return 1;
        } else return c1.cardType.name.compareTo(c2.cardType.name);
    }
}
