package cardgame.server.communication.command;

import cardgame.server.communication.BaseCommand;

public class RejectRequestGame implements BaseCommand {
    public static final String NAME = "reject_request_game";
    public String nickname;
}
