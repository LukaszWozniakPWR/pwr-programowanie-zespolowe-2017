package cardgame.server.communication.command;

import cardgame.server.communication.Action;
import cardgame.server.communication.BaseCommand;

public class PutCard implements BaseCommand {
    public int cardNumber;
    public int row;
    public static final String NAME = "put_card";
}
