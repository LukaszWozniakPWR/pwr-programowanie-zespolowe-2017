package cardgame.server.communication.response;

public class RequestGameResponse extends BooleanResponse {
    public String nickname;

    public RequestGameResponse(boolean success, String nickname) {
        super(success);
        this.nickname = nickname;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
