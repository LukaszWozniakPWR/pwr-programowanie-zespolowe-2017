package cardgame.server.communication.response;

import cardgame.server.communication.BaseResponse;

public class SetNicknameResponse implements BaseResponse {
    public Boolean success;

    public SetNicknameResponse(boolean success) {
        this.success = success;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
