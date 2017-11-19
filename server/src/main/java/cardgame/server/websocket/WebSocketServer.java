package cardgame.server.websocket;

import cardgame.server.Client;
import cardgame.server.GameServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class WebSocketServer extends org.java_websocket.server.WebSocketServer {
    private static Logger log = LogManager.getLogger();

    private Map<WebSocket, Client> clients;
    private GameServer gameServer;

    public WebSocketServer(InetSocketAddress address, GameServer gameServer) {
        super(address);
        this.gameServer = gameServer;
        clients = new HashMap<>();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        Client client = new WebSocketClient(conn);
        clients.put(conn, client);
        gameServer.onOpen(client);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        Client client = clients.get(conn);
        if (client != null) {
            gameServer.onClose(client);
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        Client client = clients.get(conn);
        if (client != null) {
            gameServer.onMessage(client, message);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        Client client = clients.get(conn);
        if (client != null) {
            gameServer.onError(client, ex);
        }
    }

    @Override
    public void onStart() {
        log.info(String.format("Listening on %s:%d", getAddress().getHostName(), getAddress().getPort()));
    }
}
