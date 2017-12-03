package cardgame.server;

import cardgame.server.communication.*;
import cardgame.server.communication.command.GetPlayers;
import cardgame.server.communication.command.RejectRequestGame;
import cardgame.server.communication.command.RequestGame;
import cardgame.server.communication.command.SetNickname;
import cardgame.server.communication.response.GameRequest;
import cardgame.server.communication.response.PlayerList;
import cardgame.server.communication.response.RequestGameResponse;
import cardgame.server.communication.response.SetNicknameResponse;
import cardgame.server.model.Game;
import cardgame.server.model.Player;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class GameServer {
    private static Logger log = LogManager.getLogger();

    private final Set<Client> clients;
    private int lastClientId = 0;
    private Gson gson;
    private final Set<Game> games;
    private static final Pattern NICKNAME_REGEX = Pattern.compile("^[a-zA-Z0-9_-]{4,24}$");

    public GameServer() {
        clients = new HashSet<>();
        games = new HashSet<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Command.class, new CommandDeserializer());
        gsonBuilder.registerTypeAdapter(Response.class, new ResponseSerializer());
        gsonBuilder.excludeFieldsWithModifiers(Modifier.PRIVATE);
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
        log.debug(String.format("Client %d sent: %s", client.getId(), message));

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

            case GetPlayers.NAME:
                handleGetPlayers(client);
                break;

            case RequestGame.NAME:
                handleRequestGame(client, (RequestGame) command.args);
                break;

            case RejectRequestGame.NAME:
                handleRejectRequestGame(client, (RejectRequestGame) command.args);
                break;

            default:
                break;
        }
    }

    public void onError(Client client, Exception ex) {
        log.info(String.format("Client %d error", client.getId()));
        ex.printStackTrace();
    }

    private Player getPlayerByName(String name) {
        Optional<Player> player;
        synchronized (clients) {
            player = clients.stream()
                    .filter(c -> c.getPlayer() != null && name.equals(c.getPlayer().name))
                    .map(Client::getPlayer).findFirst();
        }

        return player.orElse(null);
    }

    private void handleSetNickname(Client client, SetNickname args) {
        boolean success = false;
        Player p = getPlayerByName(args.nickname);
        Player player = client.getPlayer();

        if (NICKNAME_REGEX.matcher(args.nickname).matches() && (p == null || p == player)) {
            success = true;

            if (player == null) {
                player = new Player(args.nickname);
                client.setPlayer(player);
            } else {
                player.name = args.nickname;
            }

            log.debug(String.format("Client %d set nickname %s", client.getId(), args.nickname));
        }
        sendResponse(client, new SetNicknameResponse(success));
    }

    private void handleGetPlayers(Client client) {
        PlayerList players = new PlayerList();
        synchronized (clients) {
            clients.stream()
                    .filter(c -> c.getPlayer() != null && c.getPlayer() != client.getPlayer())
                    .forEach(c -> players.players.add(c.getPlayer()));
        }

        sendResponse(client, players);
    }

    private void handleRequestGame(Client client, RequestGame args) {
        Player player = client.getPlayer();

        if (player != null && player.state == Player.PlayerState.FREE) {
            Player opponent = getPlayerByName(args.nickname);

            if (opponent != null && opponent != player && opponent.state == Player.PlayerState.FREE) {
                boolean matched = false;
                synchronized (player.getGameRequsets()) {
                    if (player.getGameRequsets().remove(opponent)) {
                        // opponent requested game with player
                        matched = true;
                    }
                }

                if (matched) {
                    sendResponse(client, new RequestGameResponse(true, opponent.name));
                    sendResponse(opponent, new RequestGameResponse(true, player.name));
                    newGame(player, opponent);
                } else {
                    boolean sendRequest = false;
                    synchronized (opponent.getGameRequsets()) {
                        if (!opponent.getGameRequsets().contains(player)) {
                            opponent.getGameRequsets().add(player);
                            sendRequest = true;
                        }
                    }

                    if (sendRequest) {
                        sendResponse(opponent, new GameRequest(player.name));
                    }
                }

                return;
            }
        }

        sendResponse(client, new RequestGameResponse(false, args.nickname));
    }

    private void handleRejectRequestGame(Client client, RejectRequestGame args) {
        Player player = client.getPlayer();
        Player opponent = getPlayerByName(args.nickname);

        if (player != null && opponent != null && player != opponent) {
            boolean rejected;
            synchronized (player.getGameRequsets()) {
                rejected = player.getGameRequsets().remove(opponent);
            }

            if (rejected) {
                sendResponse(opponent, new RequestGameResponse(false, player.name));
            }
        }
    }

    private void sendResponse(Player player, BaseResponse response) {
        sendResponse(player.getClient(), response);
    }

    private void sendResponse(Client client, BaseResponse response) {
        client.send(gson.toJson(new Response(response)));
    }

    private void newGame(Player player1, Player player2) {
        player1.state = Player.PlayerState.PLAYING;
        player2.state = Player.PlayerState.PLAYING;

        games.add(new Game(player1, player2));
    }
}
