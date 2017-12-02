package cardgame.server.communication;

public class Response {
    public String type;
    public BaseResponse response;

    public Response(BaseResponse response) {
        this.response = response;
        this.type = response.getName();
    }
}
