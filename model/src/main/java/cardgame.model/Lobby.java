package cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

    public static void main(String[] args) {
//        System.out.println("The Server is Running");
        List<Card> deck1 = new ArrayList<>();
        List<Card> deck2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            deck1.add(Card.getRandom());
            deck2.add(Card.getRandom());
        }
        Player player1 = new Player(deck1);
        Player player2 = new Player(deck2);
        Game g = new Game(player1, player2);
        g.chooseStartingPlayer();
        g.clearTable();

////        new Request(player1, RequestType.PASS, null, 0).validate().takeEffect();
//        player1.deckInHands.add(new Card(new ArrayList<>(), 10, null, null));
//        new Request(player1, RequestType.PLAY, player1.deckInHands.get(0),1).validate().takeEffect();
//
//        player1.deckInHands.add(new Card(new ArrayList<>(), 15, null, null));
//        player1.deckInHands.get(0).attributes.add(Attribute.COMMANDERS_HORN);
//        new Request(player1, RequestType.PLAY, player1.deckInHands.get(0),1).validate().takeEffect();
//
//        player1.deckInHands.add(new Card(new ArrayList<>(), 1, null, null));
//        player1.deckInHands.get(0).attributes.add(Attribute.SUPPLY);
//        new Request(player1, RequestType.PLAY, player1.deckInHands.get(0),1).validate().takeEffect();
//
//        player1.deckInHands.add(new Card(new ArrayList<>(), 5, null, BondClass.NAUSICAA));
//        player1.deckInHands.get(0).attributes.add(Attribute.BOND);
//        new Request(player1, RequestType.PLAY, player1.deckInHands.get(0),2).validate().takeEffect();
//
//        player1.deckInHands.add(new Card(new ArrayList<>(), 5, null, BondClass.NAUSICAA));
//        player1.deckInHands.get(0).attributes.add(Attribute.BOND);
//        new Request(player1, RequestType.PLAY, player1.deckInHands.get(0),2).validate().takeEffect();
//
//        player1.deckInHands.add(new Card(new ArrayList<>(), 5, null, BondClass.NAUSICAA));
//        player1.deckInHands.get(0).attributes.add(Attribute.BOND);
//        new Request(player1, RequestType.PLAY, player1.deckInHands.get(0),2).validate().takeEffect();
//
//        player1.deckInHands.add(new Card(new ArrayList<>(), 2, cardgame.model.MusterClass.ELVEN_SKIRMISHER, null));
//        player1.deckInHands.add(new Card(new ArrayList<>(), 2, cardgame.model.MusterClass.ELVEN_SKIRMISHER, null));
//        player1.deckInHands.get(0).attributes.add(Attribute.MUSTER);
//        player1.deckInHands.get(1).attributes.add(Attribute.MUSTER);
//        new Request(player1, RequestType.PLAY, player1.deckInHands.get(0),3).validate().takeEffect();
//
//        player2.deckInHands.add(new Card(new ArrayList<>(), 0, null, null));
//        player2.deckInHands.get(0).attributes.add(Attribute.COLD);
//        new Request(player2, RequestType.PLAY, player2.deckInHands.get(0),0).validate().takeEffect();
//
//        player2.deckInHands.add(new Card(new ArrayList<>(), 0, null, null));
//        player2.deckInHands.get(0).attributes.add(Attribute.SCORCH);
//        new Request(player2, RequestType.PLAY, player2.deckInHands.get(0),0).validate().takeEffect();
//
////        System.out.println(""+player1.getRow(1).elements.get(0).getStrength(player1.getRow(1)));
////        System.out.println(""+player1.getRow(1).elements.get(1).getStrength(player1.getRow(1)));
////        System.out.println(""+player1.getRow(1).elements.get(2).getStrength(player1.getRow(1)));
////        g.clearTable();
//        System.out.println("Wynik gracza 1: "+player1.getRoundScore());
//        System.out.println("Liczba kart gracza 1: "+player1.deckInHands.size());
//        System.out.println("Przeszło");
    }
}
