package main.java.cardgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;


public class Row {
    public ArrayList<Card> elements = new ArrayList<>();
    public ArrayList<Effect> effects = new ArrayList<>();
    private StandardCardComparator scc = new StandardCardComparator();

    public int getScore() {
        return elements.stream().mapToInt(x -> x.getStrength(this)).sum();
    }

    public int getScourgePeak() {
        OptionalInt max = elements.stream().mapToInt(x -> x.is(Attribute.HERO) ? -1 : x.getStrength(this)).max();
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
        return isAffectedBy(Effect.COMMANDERS_HORN) || elements.stream().anyMatch(c -> c.is(Attribute.COMMANDERS_HORN));
    }

    public void clear() {
        elements.clear();
        effects.clear();
    }

    public Row add(Card c) {
        elements.add(c);
        return this;
    }

    public void sort() {
        elements.sort(scc);
    }

    public Row scourge(List<Card> graveyard, int value) {
        ArrayList<Card> toRemove = new ArrayList<>();
        for (Card c : elements)
            if (c.getStrength(this) == value && !c.is(Attribute.HERO)) {
                toRemove.add(c);
            }
        graveyard.addAll(toRemove);
        for (Card c: toRemove)
            elements.remove(c);
        return this;
    }
}
