package cardgame.server.communication.response;

import cardgame.server.communication.BaseResponse;
import cardgame.server.model.Player;

import java.util.ArrayList;

public class PlayerList extends ArrayList<Player> implements BaseResponse {
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
