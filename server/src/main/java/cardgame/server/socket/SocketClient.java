package cardgame.server.socket;

import cardgame.server.Client;
import cardgame.server.GameServer;

import java.io.*;
import java.net.Socket;

public class SocketClient extends Client implements Runnable {
    private Socket connection;
    private Thread thread;
    private GameServer gameServer;
    private BufferedReader reader;
    private OutputStreamWriter writer;

    public SocketClient(Socket socket, GameServer gameServer) {
        this.connection = socket;
        this.gameServer = gameServer;
    }

    @Override
    public void send(String message) {
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

            while ((line = reader.readLine()) != null) {
                gameServer.onMessage(this, line);
            }
        } catch (IOException e) {
            gameServer.onError(this, e);
        } finally {
            gameServer.onClose(this);
            try {
                reader.close();
                writer.close();
                connection.close();
            } catch (IOException ignored) {}
        }
    }
}
