package main.java.cardgame.model;//class CardNotFoundException extends Exception {}

public class Request {
    private Player player;
    private RequestType type;
    private Card card;
    private int row;

    public Request validate() {
        switch (type)
        {
            case PASS:      return this;
            case PLAY:      if (player.deckInHands.contains(card))  return this; else return null;
            case REVIVE:    if (player.graveyard.contains(card))    return this; else return null;
            case GET_NEW:   return this;
        }
        return null;
    }

    public void takeEffect() {
        switch (type)
        {
            case PASS: player.passed = true;            break;
            case PLAY: player.play(card, row);          break;
            case REVIVE: player.play(card, row);        break;
            case GET_NEW: player.deckInHands.add(card); break;
        }
    }

    public Request(Player p, RequestType t) {
        this.player = p;
        this.type = t;
        this.card = null;
        this.row = 0;
    }

    public Request(Player p, RequestType t, Card c, int row) {
        this.player = p;
        this.type = t;
        this.card = c;
        this.row = row;
    }
}
