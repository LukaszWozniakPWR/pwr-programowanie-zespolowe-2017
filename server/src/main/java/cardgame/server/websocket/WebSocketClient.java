package cardgame.server.websocket;

import cardgame.server.Client;
import org.java_websocket.WebSocket;

public class WebSocketClient extends Client {
    private WebSocket connection;

    public WebSocketClient(WebSocket conn) {
        this.connection = conn;
    }

    @Override
    public void send(String message) {
        connection.send(message);
    }
}
