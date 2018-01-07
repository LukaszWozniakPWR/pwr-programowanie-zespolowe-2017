package cardgame.server.communication.response;

import cardgame.server.communication.BaseResponse;

public class GameEndedResponse implements BaseResponse {
    public GameEndReason reason;

    public GameEndedResponse(GameEndReason reason) {
        this.reason = reason;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
