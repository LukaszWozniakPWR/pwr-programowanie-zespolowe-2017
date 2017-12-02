package cardgame.server.communication.command;

import cardgame.server.communication.BaseCommand;

public class SetNickname implements BaseCommand {
    public static final String NAME = "set_nickname";
    public String nickname;
}
