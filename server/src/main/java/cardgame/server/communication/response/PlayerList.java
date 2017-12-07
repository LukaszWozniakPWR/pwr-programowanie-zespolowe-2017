package cardgame.server.communication.response;

import cardgame.server.communication.BaseResponse;
import cardgame.server.model.User;
import java.util.ArrayList;
import java.util.List;

public class PlayerList implements BaseResponse {
    public List<User> players = new ArrayList<>();

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
