package cardgame.server;

import cardgame.server.socket.SocketServer;
import cardgame.server.websocket.WebSocketServer;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;

public class Server {
    private SocketServer socketServer;
    private WebSocketServer webSocketServer;
    private GameServer gameServer;

    public Server() throws IOException {
        Properties config = new Properties();
        config.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        gameServer = new GameServer();

        socketServer = new SocketServer(new InetSocketAddress(
                config.getProperty("hostname"),
                Integer.parseInt(config.getProperty("socket_port"))
        ), gameServer);

        webSocketServer = new WebSocketServer(new InetSocketAddress(
                config.getProperty("hostname"),
                Integer.parseInt(config.getProperty("websocket_port"))
        ), gameServer);
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();
    }

    public void start() {
        socketServer.start();
        webSocketServer.start();
    }
}
