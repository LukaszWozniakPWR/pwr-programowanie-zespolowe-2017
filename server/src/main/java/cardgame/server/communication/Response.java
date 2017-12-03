package cardgame.server.communication;

public class Response {
    public String type;
    private BaseResponse response;

    public Response(BaseResponse response) {
        this.response = response;
        this.type = response.getName();
    }

    public BaseResponse getResponse() {
        return response;
    }
}
