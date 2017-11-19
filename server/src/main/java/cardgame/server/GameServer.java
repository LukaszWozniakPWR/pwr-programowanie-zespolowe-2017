package cardgame.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class GameServer {
    private static Logger log = LogManager.getLogger();

    private Set<Client> clients;
    private int lastClientId = 0;

    public GameServer() {
        clients = new HashSet<>();
    }

    public void onOpen(Client client) {
        log.info(String.format("Client %x connected", client.hashCode()));
        client.setId(++lastClientId);
        clients.add(client);
        log.info(String.format("Client %x is now %d", client.hashCode(), client.getId()));
    }

    public void onClose(Client client) {
        log.info(String.format("Client %d disconnected", client.getId()));
        clients.remove(client);
    }

    public void onMessage(Client client, String message) {
        log.info(String.format("Client %d sent: %s", client.getId(), message));
    }

    public void onError(Client client, Exception ex) {
        log.info(String.format("Client %d error", client.getId()));
        ex.printStackTrace();
    }
}
