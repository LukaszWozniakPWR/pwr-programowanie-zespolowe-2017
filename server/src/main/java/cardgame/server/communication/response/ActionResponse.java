package cardgame.server.communication.response;

import cardgame.server.communication.Action;
import cardgame.server.communication.BaseResponse;
import cardgame.server.model.GameState;

public class ActionResponse extends BooleanResponse implements BaseResponse {
    public GameState game;
    private Action action;

    public ActionResponse(Action action, boolean success, GameState state) {
        super(success);
        game = state;
        this.action = action;
    }

    @Override
    public String getName() {
        return action.RESPONSE_NAME + "Response";
    }
}
