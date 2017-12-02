package cardgame.server;

import java.io.IOException;

public abstract class Client {
    private int id = -1;

    public abstract void send(String message) throws IOException;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
