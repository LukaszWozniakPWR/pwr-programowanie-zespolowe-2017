package cardgame.server.communication.response;

import cardgame.server.communication.BaseResponse;

public class OpponentActionResponse extends GameStateResponse implements BaseResponse {
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
