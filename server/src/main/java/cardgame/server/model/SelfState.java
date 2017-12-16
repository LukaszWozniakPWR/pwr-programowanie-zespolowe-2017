package cardgame.server.model;

import cardgame.model.Card;
import cardgame.model.Player;

import java.util.List;

public class SelfState extends OpponentState {
    public List<Card> hand;
    public List<Card> graveyard;

    public SelfState(Player player) {
        super(player);
        this.hand = player.deckInHands;
        this.graveyard = player.graveyard;
    }
}
