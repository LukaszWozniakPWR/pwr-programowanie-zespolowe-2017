package cardgame.server.communication;

import cardgame.model.Row;
import cardgame.server.model.CardData;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class RowSerializer implements JsonSerializer<Row> {

    @Override
    public JsonElement serialize(Row src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("points", src.getScore());
        object.add("effects", context.serialize(src.effects));
        object.add("elements", context.serialize(src.elements.stream().map((card -> new CardData(card, src))).toArray(CardData[]::new)));
        return object;
    }
}
