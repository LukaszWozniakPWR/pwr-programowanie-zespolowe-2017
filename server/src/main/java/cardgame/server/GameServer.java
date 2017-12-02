package cardgame.server;

import cardgame.server.communication.BaseResponse;
import cardgame.server.communication.Command;
import cardgame.server.communication.CommandDeserializer;
import cardgame.server.communication.Response;
import cardgame.server.communication.command.SetNickname;
import cardgame.server.communication.response.SetNicknameResponse;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class GameServer {
    private static Logger log = LogManager.getLogger();

    private final Set<Client> clients;
    private int lastClientId = 0;
    private Gson gson;

    public GameServer() {
        clients = new HashSet<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Command.class, new CommandDeserializer());
        gson = gsonBuilder.create();
    }

    public void onOpen(Client client) {
        log.info(String.format("Client %x connected", client.hashCode()));
        client.setId(++lastClientId);

        synchronized (clients) {
            clients.add(client);
        }

        log.info(String.format("Client %x is now %d", client.hashCode(), client.getId()));
    }

    public void onClose(Client client) {
        log.info(String.format("Client %d disconnected", client.getId()));
        synchronized (clients) {
            clients.remove(client);
        }
    }

    public void onMessage(Client client, String message) {
        Command command;
        try {
            command = gson.fromJson(message, Command.class);
        } catch (JsonSyntaxException e) {
            return;
        }

        if (command == null) {
            return;
        }

        switch (command.command) {
            case SetNickname.NAME:
                handleSetNickname(client, (SetNickname) command.args);
                break;

            default:
                break;
        }
    }

    public void onError(Client client, Exception ex) {
        log.info(String.format("Client %d error", client.getId()));
        ex.printStackTrace();
    }

    private void handleSetNickname(Client client, SetNickname args) {
        synchronized (clients) {
            for (Client c: clients) {
                if (args.nickname.equals(c.getNickname())) {
                    sendResponse(client, new SetNicknameResponse(false));
                    return;
                }
            }

            client.setNickname(args.nickname);
            log.debug(String.format("Client %d set nickname %s", client.getId(), args.nickname));
            sendResponse(client, new SetNicknameResponse(true));
        }
    }

    private void sendResponse(Client client, BaseResponse response) {
        client.send(gson.toJson(new Response(response)));
    }
}
