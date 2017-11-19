package cardgame.server;

public abstract class Client {
    private int id = -1;

    public abstract void send(String message);

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
