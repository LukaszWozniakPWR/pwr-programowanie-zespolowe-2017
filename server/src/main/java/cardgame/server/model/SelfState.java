package cardgame.server.model;

import cardgame.model.Card;
import cardgame.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SelfState extends OpponentState {
    public List<CardData> hand;
    public List<CardData> graveyard;

    public SelfState(Player player) {
        super(player);
        this.hand = player.deckInHands.stream().map(card -> new CardData(card, null)).collect(Collectors.toList());
        this.graveyard = new ArrayList<>();
//        this.graveyard = player.graveyard.stream().map(card -> new CardData(card, null)).collect(Collectors.toList());
    }
}
