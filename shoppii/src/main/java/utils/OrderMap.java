package utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.Order;
import model.OrderItem;

public class OrderMap implements JsonDeserializer<HashMap<Order, ArrayList<OrderItem>>>, JsonSerializer<HashMap<Order, ArrayList<OrderItem>>> {

    @Override
    public JsonElement serialize(HashMap<Order, ArrayList<OrderItem>> src, Type typeOfSrc,
            JsonSerializationContext context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HashMap<Order, ArrayList<OrderItem>> deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
