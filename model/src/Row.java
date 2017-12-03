import java.util.ArrayList;
import java.util.Comparator;
import java.util.OptionalInt;


class Row {
    public ArrayList<Card> elements = new ArrayList<>();
    private ArrayList<Effect> effects = new ArrayList<>();

    public int getScore() {
        return elements.stream().mapToInt(x -> x.getStrength(this)).sum();
    }

    public int getScourgePeak() {
        OptionalInt max = elements.stream().mapToInt(x -> x.cardType.is(Attribute.HERO) ? -1 : x.basicStrength).max();
        if (max.isPresent())
            return max.getAsInt();
        else
            return -1;
    }

    public void affect(Effect e) {
        if (!isAffectedBy(e))
            effects.add(e);
    }

    public void disaffect(Effect e) {
        effects.remove(e);
    }

    public boolean isAffectedBy(Effect e) {
        return effects.contains(e);
    }

    public boolean hasHorn() {
        return isAffectedBy(Effect.COMMANDERS_HORN) || elements.stream().anyMatch(c -> c.cardType.is(Attribute.COMMANDERS_HORN));
    }

    public void clear() {
        elements.clear();
        effects.clear();
    }

    public Row add(Card c) {
        elements.add(c);
        return this;
    }

    public void sort(Comparator<Card> c) {
        elements.sort(c);
    }

    public void scourge(ArrayList<Card> graveyard, int value) {
        for (Card c : elements)
            if (c.basicStrength == value && !c.cardType.is(Attribute.HERO)) {
                graveyard.add(c);
                elements.remove(c);
            }
    }
}
