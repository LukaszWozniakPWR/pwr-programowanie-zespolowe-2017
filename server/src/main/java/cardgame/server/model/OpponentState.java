package cardgame.server.model;

import cardgame.model.Player;
import cardgame.model.Row;

public class OpponentState {
    public Boolean passed;
    public int score;
    public Row frontRow;
    public Row middleRow;
    public Row rearRow;

    public OpponentState(Player player) {
        this.passed = player.passed;
        this.score = player.gameScore;
        this.frontRow = player.frontRow;
        this.middleRow = player.middleRow;
        this.rearRow = player.rearRow;
    }
}
