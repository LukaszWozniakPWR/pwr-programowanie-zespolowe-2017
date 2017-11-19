package cardgame.server.socket;

import cardgame.server.Client;
import cardgame.server.GameServer;

import java.io.IOException;
import java.net.Socket;

public class SocketClient extends Client implements Runnable {
    private Socket connection;
    private Thread thread;
    private GameServer gameServer;

    public SocketClient(Socket socket, GameServer gameServer) {
        this.connection = socket;
        this.gameServer = gameServer;
    }

    @Override
    public void send(String message) {
        // todo
    }

    @Override
    public void run() {
        synchronized (this) {
            if (thread != null)
                throw new IllegalStateException(getClass().getName() + " can only be started once.");
            thread = Thread.currentThread();
        }

        gameServer.onOpen(this);

        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            gameServer.onClose(this);
        }
    }
}
