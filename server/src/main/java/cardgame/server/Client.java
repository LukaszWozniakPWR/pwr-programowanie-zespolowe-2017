package cardgame.server;

public abstract class Client {
    private int id = -1;
    private String nickname;

    public abstract void send(String message);

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
