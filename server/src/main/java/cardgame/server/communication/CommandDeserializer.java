package cardgame.server.communication;

import cardgame.server.communication.command.SetNickname;
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

            default:
                break;

        }

        return command;
    }
}
