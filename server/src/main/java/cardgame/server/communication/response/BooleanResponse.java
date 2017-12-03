package cardgame.server.communication.response;

import cardgame.server.communication.BaseResponse;

public class BooleanResponse implements BaseResponse {
    public Boolean success;

    public BooleanResponse(boolean success) {
        this.success = success;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
