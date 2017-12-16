package cardgame.server.socket;

import cardgame.server.GameServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
    private static Logger log = LogManager.getLogger();

    private InetSocketAddress address;
    private ServerSocket serverSocket;
    private Thread thread;
    private GameServer gameServer;

    public SocketServer(InetSocketAddress address, GameServer gameServer) {
        this.gameServer = gameServer;
        this.address = address;
    }

    public void start() {
        if (thread != null)
            throw new IllegalStateException(getClass().getName() + " can only be started once.");
        new Thread(this).start();
    }

    @Override
    public void run() {
        synchronized (this) {
            if (thread != null)
                throw new IllegalStateException(getClass().getName() + " can only be started once.");
            thread = Thread.currentThread();
        }

        try {
            serverSocket = new ServerSocket(this.address.getPort(), 0, this.address.getAddress());
            serverSocket.setReuseAddress(true);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        log.info(String.format("Listening on %s:%d", address.getHostName(), address.getPort()));

        while (!thread.isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                SocketClient client = new SocketClient(socket, gameServer);
                new Thread(client).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
