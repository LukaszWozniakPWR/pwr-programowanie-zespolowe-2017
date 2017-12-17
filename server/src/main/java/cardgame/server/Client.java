package cardgame.server;

import cardgame.server.model.User;

public abstract class Client {
    private int id = -1;
    private User user;

    public abstract void send(String message);

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.setClient(this);
    }
}
