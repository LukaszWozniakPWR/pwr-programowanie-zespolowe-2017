package cardgame.model;

public class Lobby {

    public static void main(String[] args) {
        Player p = new Player(2);
        for (Card c : p.deckInHands)
            System.out.println(c.name());
    }
}
