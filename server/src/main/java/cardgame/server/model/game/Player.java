package cardgame.server.model.game;
import java.util.List;

public interface Player {
    List<Card> getHand();
    List<Card> getGraveyard();
    List<Row> getRows();
    int getScore();
    void pass() throws NotPlayersTurnException;
    void putCard(Card card, RowType row) throws NotPlayersTurnException, CardNotExistsException;
}
