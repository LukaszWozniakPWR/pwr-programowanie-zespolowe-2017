package cardgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Player {
    public int gameScore;
    public Boolean passed = false;
    public Player opponent;
    public Card lastPlayedCard;
    public Game game;

    public List<Card> deckInHands = new ArrayList<>(), graveyard = new ArrayList<>();
    public Row frontRow = new Row(), middleRow = new Row(), rearRow = new Row();

    public int getRoundScore() {
        return frontRow.getScore() + middleRow.getScore() + rearRow.getScore();
    }

    public Row getRow(int row) {
        switch (row)
        {
            case 1: return frontRow;
            case 2: return middleRow;
            case 3: return rearRow;
            default:return null;
        }
    }

    private void copyFromRowsToGraveyard() {
        for (int i = 1; i <= 3; i++)
            graveyard.addAll(getRow(i).elements);
    }

    private void clearRows() {
        for (int i = 1; i <= 3; i++)
            getRow(i).clear();
    }

    private void moveCardsToGraveyard() {
        copyFromRowsToGraveyard();
        clearRows();
    }

    public void clear() {
        passed = false;
        moveCardsToGraveyard();
    }

    public void pass() {
        passed = true;
    }

    public void scourge() {
        int scourgePeak = Stream.of(frontRow, middleRow, rearRow).mapToInt(Row::getScourgePeak).max().getAsInt();
//        System.out.println(""+scourgePeak);
        if (scourgePeak >= 0)
            for (int i = 1; i <= 3; i++)
                getRow(i).scourge(graveyard, scourgePeak).sort();
    }

    // see Attribute::MUSTER before changing
    public void playAll(Card c, int row) {
        ArrayList<Card> toPlay = new ArrayList<>();
        for (Card cc : deckInHands)
            if (cc.musterClass == c.musterClass) {
//                getRow(row).add(cc).sort();
//                deckInHands.remove(cc);
                toPlay.add(cc);
            }
        for (Card cc : toPlay) {
            getRow(row).add(cc);
            deckInHands.remove(cc);
        }
        getRow(row).sort();
    }

    public void scorch(int row) {
        getRow(row).scourge(graveyard, frontRow.getScourgePeak()).sort();
    }

    public void play(Card c, int row) throws Game.InvalidMove {
        lastPlayedCard = c;
        if (row > 0) {
            getRow(row).add(c).sort();
        }
        deckInHands.remove(c);
        c.specialActions(this, row);
    }

    public Player(List<Card> deckGiven) {
        deckInHands = deckGiven;
    }
}
