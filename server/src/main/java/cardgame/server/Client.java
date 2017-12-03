package cardgame.server;

import cardgame.server.model.Player;

public abstract class Client {
    private int id = -1;
    private Player player;

    public abstract void send(String message);

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.setClient(this);
    }
}
