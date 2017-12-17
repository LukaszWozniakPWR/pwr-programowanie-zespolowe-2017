package cardgame.model;

import java.util.Random;

public class Game {
    private Player player1, player2, currentPlayer;

    private void chooseStartingPlayer() {
        currentPlayer = (new Random().nextDouble() > 0.5) ? player1 : player2;
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

    private void clearTable() {
        player1.clear();
        player2.clear();
    }

    private void switchPlayers() {
        currentPlayer = currentPlayer.opponent;
    }

    private void round() {
        while (!player1.passed || !player2.passed) {
            currentPlayer.getRequest().validate().takeEffect();
            if (!currentPlayer.opponent.passed)
                switchPlayers();
        }

        Player winner = determineRoundWinner();
        if (winner != null) {
            ++winner.gameScore;
            currentPlayer = winner;
        } else {
            // TODO kto zaczyna następną rundę, jeśli nie ma zwycięzcy?
        }

        clearTable();
    }

    // TODO POTRZEBNY PROTOKÓŁ KOMUNIKACJI pobierz talie od graczy
    private void getDecksFromPlayers() {}

    // TODO POTRZEBA USTALIĆ TALIE sprawdź poprawność talii
    private void validateDecks() {}

    // TODO POTRZEBNY PROTOKÓŁ KOMUNIKACJI ogłoszenie zwycięzcy
    private void announceWinner() {

    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void play() {
        getDecksFromPlayers();
        validateDecks();
        chooseStartingPlayer();
        while (player1.gameScore < 2 && player2.gameScore < 2) {
            round();
        }
        announceWinner();
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.opponent = player2;
        player2.opponent = player1;
    }
}
