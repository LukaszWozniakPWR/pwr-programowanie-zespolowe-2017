package cardgame.server.model.game;

import java.util.List;

public interface Row {
    List<Card> getCards();
    int getScore();
    RowType getType();
}
