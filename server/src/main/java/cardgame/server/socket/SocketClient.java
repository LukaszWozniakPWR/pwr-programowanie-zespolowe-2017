package cardgame.server.socket;

import cardgame.server.Client;
import cardgame.server.GameServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class SocketClient extends Client implements Runnable {
    private Socket connection;
    private Thread thread;
    private GameServer gameServer;
    private BufferedReader reader;
    private OutputStreamWriter writer;
    private static Logger log = LogManager.getLogger();

    public SocketClient(Socket socket, GameServer gameServer) {
        this.connection = socket;
        try {
            this.connection.setSoTimeout(5000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        this.gameServer = gameServer;
    }

    @Override
    public void send(String message) {
        log.debug("Sending: " + message);
        try {
            writer.write(message);
            writer.write('\n');
            writer.flush();
        } catch (IOException e) {
            gameServer.onError(this, e);
        }
    }

    @Override
    public void run() {
        synchronized (this) {
            if (thread != null)
                throw new IllegalStateException(getClass().getName() + " can only be started once.");
            thread = Thread.currentThread();
        }

        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            writer = new OutputStreamWriter(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        gameServer.onOpen(this);

        try {
            String line;

            // state:
            // 0 - ok
            // 1 - ping sent, waiting for response
            // 2 - client disconnected
            int state = 0;

            while (state < 2) {
                try {
                    while ((line = reader.readLine()) != null) {
                        state = 0;
                        gameServer.onMessage(this, line);
                    }
                    log.debug("client closed connection");
                    state = 2;
                } catch (SocketTimeoutException ignored) {
                    if (++state < 2) send("{\"type\":\"Ping\",\"Ping\":{}}");
                    else log.debug("connection timeout");
                }
            }
        } catch (IOException e) {
            gameServer.onError(this, e);
        } finally {
            log.debug("closing connection");
            gameServer.onClose(this);
            try {
                reader.close();
                writer.close();
                connection.close();
                log.debug("connection closed");
            } catch (IOException ignored) {}
        }
    }
}
