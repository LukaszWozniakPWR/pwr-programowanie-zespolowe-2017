package cardgame.server.communication;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ResponseSerializer implements JsonSerializer<Response> {

    @Override
    public JsonElement serialize(Response src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("type", src.type);
        object.add(src.type, context.serialize(src.getResponse()));
        return object;
    }
}
