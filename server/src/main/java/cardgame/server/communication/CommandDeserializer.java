package cardgame.server.communication;

import cardgame.server.communication.command.*;
import cardgame.server.communication.response.RequestGameResponse;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CommandDeserializer implements JsonDeserializer<Command> {

    @Override
    public Command deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Command command = new Command();
        JsonObject object = json.getAsJsonObject();
        command.command = object.get("command").getAsString();

        switch (command.command) {
            case SetNickname.NAME:
                command.args = context.deserialize(object.get("args"), SetNickname.class);
                break;
            case GetPlayers.NAME:
                command.args = null;
                break;
            case RequestGame.NAME:
                command.args = context.deserialize(object.get("args"), RequestGame.class);
                break;
            case RejectRequestGame.NAME:
                command.args = context.deserialize(object.get("args"), RejectRequestGame.class);
                break;
            case Pong.NAME:
                command.args = null;
                break;
            case PutCard.NAME:
                command.args = context.deserialize(object.get("args"), PutCard.class);
                break;
            case Pass.NAME:
                command.args = null;
                break;
            default:
                break;
        }

        return command;
    }
}
