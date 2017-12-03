
class Request {
    private Player player;
    private RequestType type;
    private Card card;
    private int row;

    public void takeEffect() {
        switch (type)
        {
            case PASS: player.passed = true;    break;
            case PLAY: player.play(card, row);  break;
        }
    }

    public Request(Player p, RequestType t) {
        this.player = p;
        this.type = t;
        this.card = null;
        this.row = 0;
    }

    public Request(Player p, RequestType t, Card c, int row) {
        this.player = p;
        this.type = t;
        this.card = c;
        this.row = row;
    }
}
