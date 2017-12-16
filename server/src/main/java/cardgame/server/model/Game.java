package cardgame.server.model;

import cardgame.model.Player;

public class Game extends cardgame.model.Game {
    private User user1;
    private User user2;

    public Game(Player player1, Player player2) {
        super(player1, player2);
    }

    public Game(User user1, User user2) {
        this(user1.getPlayer(), user2.getPlayer());
        this.user1 = user1;
        this.user2 = user2;

        user1.setGame(this);
        user2.setGame(this);
    }

    public User getOpponent(User user) {
        return (user == user1) ? user2 : user1;
    }
}
