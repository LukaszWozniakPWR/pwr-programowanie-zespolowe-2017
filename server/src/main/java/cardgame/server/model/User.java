package cardgame.server.model;

import cardgame.model.Player;
import cardgame.server.Client;

import java.util.HashSet;
import java.util.Set;

public class User {
    public String name;
    public PlayerState state = PlayerState.FREE;
    private Client client;
    private final Set<User> gameRequsets;
    private Game game = null;
    private Player player;

    public User(String name) {
        this.name = name;
        this.gameRequsets = new HashSet<>();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<User> getGameRequsets() {
        return gameRequsets;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void updateState() {
        if (game == null) {
            state = PlayerState.FREE;
        } else {
            state = PlayerState.PLAYING;
        }
    }

    public enum PlayerState {
        FREE,
        PLAYING
    }
}
