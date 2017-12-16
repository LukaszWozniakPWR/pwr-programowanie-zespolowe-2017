package cardgame.server.communication.response;

public class SetNicknameResponse extends BooleanResponse {
    public SetNicknameResponse(boolean success) {
        super(success);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
