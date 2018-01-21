package cardgame.model;

public class Game {
    public class InvalidMove extends Exception {
        public InvalidMove(String message) {
            super(message);
        }
    }

    public Player player1, player2, currentPlayer;

    public void chooseStartingPlayer() {
        currentPlayer = (Math.random() > 0.5) ? player1 : player2;
    }

    private Player determineRoundWinner() {
        int p1s = player1.getRoundScore(), p2s = player2.getRoundScore();
        if (p1s > p2s)
            return player1;
        else if (p1s < p2s)
            return player2;
        else
            return null;
    }

    public void clearTable() {
        player1.clear();
        player2.clear();
    }

    private void switchPlayers() {
        if (!currentPlayer.opponent.passed)
            currentPlayer = currentPlayer.opponent;
    }

    public void pass(Player p) throws InvalidMove {
        if (p == currentPlayer) {
            p.pass();
            switchPlayers();
        } else {
            throw new InvalidMove("Wrong player");
        }
    }

    public boolean roundIsOver() {
        return player1.passed && player2.passed;
    }

    public boolean gameIsOver() {
        return player1.gameScore == 2 || player2.gameScore == 2;
    }

    public void putCard(Player p, Card c, int row) throws InvalidMove {
        if (p == currentPlayer) {
            if (p.deckInHands.contains((c))) {
                if (c.rowsAllowed.contains(row)) {
                    if (c.is(Attribute.SPY)) {
                        p.opponent.getRow(row).add(c).sort();
                        p.deckInHands.remove(c);
                        c.specialActions(p, row);
                    }
                    else
                        p.play(c, row);
                    switchPlayers();
                } else {
                    throw new InvalidMove("Card not allowed in this row");
                }
            } else {
                throw new InvalidMove("Card not in hand");
            }
        } else {
            throw new InvalidMove("Wrong player");
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1.opponent = player2;
        this.player2.opponent = player1;
    }
}
