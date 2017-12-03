package cardgame.server.model;

import cardgame.server.Client;

import java.util.HashSet;
import java.util.Set;

public class Player {
    public final String name;
    public PlayerState state = PlayerState.FREE;
    private Client client;
    private final Set<Player> gameRequsets;
    private Game game;

    public Player(String name) {
        this.name = name;
        this.gameRequsets = new HashSet<>();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Player> getGameRequsets() {
        return gameRequsets;
    }

    public enum PlayerState {
        FREE,
        PLAYING
    }
}
