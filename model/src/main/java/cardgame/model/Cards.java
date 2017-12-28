package cardgame.model;

import java.util.ArrayList;
import java.util.Arrays;


public enum Cards {
    ARCHER(new Card(new ArrayList<>(), 5, null, null)),

    TEST1(new Card(new ArrayList<Attribute>(Arrays.asList(Attribute.SPY)), 2,
            null, null)),

    TEST2(new Card(new ArrayList<>(), 3, MusterClass.NEKKER, null)),

    TEST3(new Card(new ArrayList<Attribute>(Arrays.asList(Attribute.BOND)), 4,
            null, BondClass.NAUSICAA)),

    TEST4(new Card(new ArrayList<Attribute>(Arrays.asList(Attribute.MUSTER)), 5,
            MusterClass.ELVEN_SKIRMISHER, null)),

    TEST5(new Card(new ArrayList<Attribute>(Arrays.asList(Attribute.SCORCH)), 5,
            null, BondClass.NAUSICAA));

    private Card card;
    Cards(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
