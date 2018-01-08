package cardgame.server.model;

public class GameState {
    public User opponent;
    public User self;
    public OpponentState opponentState;
    public SelfState selfState;
    public Turn turn;

    public void forUser(User user) {
        Game game = user.getGame();
        self = user;

        if (game != null) {
            opponent = game.getOpponent(user);
            opponentState = new OpponentState(opponent.getPlayer());
            selfState = new SelfState(user.getPlayer());
            turn = game.getCurrentPlayer() == user.getPlayer() ? Turn.YOUR : Turn.OPPONENT;
        }
    }
}
