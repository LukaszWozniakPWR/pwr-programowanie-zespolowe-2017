package cardgame.server;

import cardgame.server.communication.*;
import cardgame.server.communication.command.*;
import cardgame.server.communication.response.GameRequest;
import cardgame.server.communication.response.PlayerList;
import cardgame.server.communication.response.RequestGameResponse;
import cardgame.server.communication.response.SetNicknameResponse;
import cardgame.server.model.game.Game;
import cardgame.server.model.User;
import cardgame.server.model.game.Player;
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

            case Pong.NAME:
                break;

            default:
                break;
        }
    }

    public void onError(Client client, Exception ex) {
        log.info(String.format("Client %d error", client.getId()));
        ex.printStackTrace();
    }

    private User getPlayerByName(String name) {
        Optional<User> player;
        synchronized (clients) {
            player = clients.stream()
                    .filter(c -> c.getUser() != null && name.equals(c.getUser().name))
                    .map(Client::getUser).findFirst();
        }

        return player.orElse(null);
    }

    private void handleSetNickname(Client client, SetNickname args) {
        boolean success = false;
        User p = getPlayerByName(args.nickname);
        User user = client.getUser();

        if (NICKNAME_REGEX.matcher(args.nickname).matches() && (p == null || p == user)) {
            success = true;

            if (user == null) {
                user = new User(args.nickname);
                client.setUser(user);
            } else {
                user.name = args.nickname;
            }

            log.debug(String.format("Client %d set nickname %s", client.getId(), args.nickname));
        }
        sendResponse(client, new SetNicknameResponse(success));
    }

    private void handleGetPlayers(Client client) {
        PlayerList players = new PlayerList();
        synchronized (clients) {
            clients.stream()
                    .filter(c -> c.getUser() != null && c.getUser() != client.getUser())
                    .forEach(c -> players.players.add(c.getUser()));
        }

        sendResponse(client, players);
    }

    private void handleRequestGame(Client client, RequestGame args) {
        User user = client.getUser();

        if (user != null && user.state == User.PlayerState.FREE) {
            User opponent = getPlayerByName(args.nickname);

            if (opponent != null && opponent != user && opponent.state == User.PlayerState.FREE) {
                boolean matched = false;
                synchronized (user.getGameRequsets()) {
                    if (user.getGameRequsets().remove(opponent)) {
                        // opponent requested game with player
                        matched = true;
                    }
                }

                if (matched) {
                    sendResponse(client, new RequestGameResponse(true, opponent.name));
                    sendResponse(opponent, new RequestGameResponse(true, user.name));
                    newGame(user, opponent);
                } else {
                    boolean sendRequest = false;
                    synchronized (opponent.getGameRequsets()) {
                        if (!opponent.getGameRequsets().contains(user)) {
                            opponent.getGameRequsets().add(user);
                            sendRequest = true;
                        }
                    }

                    if (sendRequest) {
                        sendResponse(opponent, new GameRequest(user.name));
                    }
                }

                return;
            }
        }

        sendResponse(client, new RequestGameResponse(false, args.nickname));
    }

    private void handleRejectRequestGame(Client client, RejectRequestGame args) {
        User user = client.getUser();
        User opponent = getPlayerByName(args.nickname);

        if (user != null && opponent != null && user != opponent) {
            boolean rejected;
            synchronized (user.getGameRequsets()) {
                rejected = user.getGameRequsets().remove(opponent);
            }

            if (rejected) {
                sendResponse(opponent, new RequestGameResponse(false, user.name));
            }
        }
    }

    private void sendResponse(User user, BaseResponse response) {
        sendResponse(user.getClient(), response);
    }

    private void sendResponse(Client client, BaseResponse response) {
        client.send(gson.toJson(new Response(response)));
    }

    private void newGame(User user1, User user2) {
        user1.state = User.PlayerState.PLAYING;
        user2.state = User.PlayerState.PLAYING;
//        Game game = null;
//        user1.setGame(game);
//        user2.setGame(game);
//        user1.setPlayer(game.getPlayer1());
//        user2.setPlayer(game.getPlayer2());
//
//        games.add(game);
    }
}
