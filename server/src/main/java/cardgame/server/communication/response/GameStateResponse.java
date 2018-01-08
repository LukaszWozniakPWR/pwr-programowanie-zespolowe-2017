package cardgame.server.communication.response;

import cardgame.server.communication.BaseResponse;
import cardgame.server.model.GameState;

public class GameStateResponse extends GameState implements BaseResponse {
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
