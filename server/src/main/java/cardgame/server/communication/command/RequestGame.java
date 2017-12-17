package cardgame.server.communication.command;

import cardgame.server.communication.BaseCommand;

public class RequestGame implements BaseCommand {
    public static final String NAME = "request_game";
    public String nickname;
}
