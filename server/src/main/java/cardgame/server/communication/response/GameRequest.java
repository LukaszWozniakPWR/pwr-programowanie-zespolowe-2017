package cardgame.server.communication.response;

import cardgame.server.communication.BaseResponse;

public class GameRequest implements BaseResponse {
    public String nickname;

    public GameRequest(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getName() {
        return "RequestGame";
    }
}
