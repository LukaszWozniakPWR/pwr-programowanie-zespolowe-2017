import java.util.ArrayList;
import java.util.stream.Stream;


class Player {
    public int gameScore;
    public Boolean passed = false;
    public Player opponent;

    private ArrayList<Card> deckInHands = new ArrayList<>(), graveyard = new ArrayList<>();
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

    public void scourge() {
        int scourgePeak = Stream.of(frontRow, middleRow, rearRow).mapToInt(Row::getScourgePeak).max().getAsInt();
        if (scourgePeak >= 0)
            for (int i = 1; i <= 3; i++)
                getRow(i).scourge(graveyard, scourgePeak);
    }

    // TODO POTRZEBNY PROTOKÓŁ KOMUNIKACJI odbieranie żądania i zamiana JSON -> Request
    public Request getRequest() {
        return null;
    }

    public void play(Card c, int row) {
        deckInHands.remove(c);
        if (row > 0) {
            getRow(row).add(c).sort(new StandardCardComparator());
        }
        c.specialAction(this, row);
    }
}
