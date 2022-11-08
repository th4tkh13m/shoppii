package utils;

import java.lang.reflect.Type;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.Address;
import model.Category;
import model.Order;
import model.Product;
import model.Shop;

public class OrderMap implements JsonDeserializer<HashMap<Order, HashMap<Product, Integer>>>, JsonSerializer<HashMap<Order, HashMap<Product, Integer>>> {

    @Override
    public JsonElement serialize(HashMap<Order, HashMap<Product, Integer>> src, Type typeOfSrc,
            JsonSerializationContext context) {
        Gson gson = new Gson();
        JsonArray orderList = new JsonArray();
        
        for (Order order : src.keySet()) {
            Shop shop = null;
            JsonObject orderJson = gson.toJsonTree(order).getAsJsonObject();
            // Output java.sql.Time as "YYYY-MM-DD HH:MM:SS"
            orderJson.addProperty("orderTime", order.getTime().toString());

            JsonArray orderItemList = new JsonArray();
            for (Product product : src.get(order).keySet()) {
                JsonObject jsonProduct = gson.toJsonTree(product).getAsJsonObject();
                jsonProduct.addProperty("orderQuantity", src.get(order).get(product));
                jsonProduct.remove("shop");
                orderItemList.add(jsonProduct);
                shop = product.getShop();
            }
            orderJson.add("shop", gson.toJsonTree(shop).getAsJsonObject());
            orderJson.add("items", orderItemList);
            orderList.add(orderJson);
        }
        return orderList;
    }

    @Override
    public HashMap<Order, HashMap<Product, Integer>> deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonNull()) {
            return null;
        } else {
            HashMap<Order, HashMap<Product, Integer>> orders = new HashMap<>();
            JsonArray orderJsonArray = json.getAsJsonArray();

            for (JsonElement orderJsonElement : orderJsonArray) {
                HashMap<Product, Integer> items = new HashMap<>();

                JsonObject orderJsonObject = orderJsonElement.getAsJsonObject();
                int orderId = Integer.parseInt(orderJsonObject.get("orderId").getAsString());
                int customerId = Integer.parseInt(orderJsonObject.get("customerId").getAsString());
                String paymentMethod = orderJsonObject.get("paymentMethod").getAsString();
                String status = orderJsonObject.get("status").getAsString();
                Timestamp time = Timestamp.valueOf(orderJsonObject.get("time").getAsString());
                
                
                // Address
                JsonObject addressJsonObject = orderJsonObject.get("address").getAsJsonObject();
                int addressId = Integer.parseInt(addressJsonObject.get("addressId").getAsString());
                String receiverAddress = addressJsonObject.get("receiverAddress").getAsString();
                String receiverName = addressJsonObject.get("receiverName").getAsString();
                String receiverPhone = addressJsonObject.get("receiverPhone").getAsString();
                String province = addressJsonObject.get("province").getAsString();
                String ward = addressJsonObject.get("ward").getAsString();
                String district = addressJsonObject.get("district").getAsString();
                boolean isDefault = addressJsonObject.get("isDefault").getAsBoolean();

                // Shop
                JsonObject shopJsonObject = orderJsonObject.get("shop").getAsJsonObject();
                int shopId = Integer.parseInt(shopJsonObject.get("shopId").getAsString());
                String shopName = shopJsonObject.get("name").getAsString();
                String shopAddress = shopJsonObject.get("address").getAsString();
                String description = shopJsonObject.get("description").getAsString();
                boolean shopStatus = shopJsonObject.get("status").getAsBoolean();

                Address address = new Address(addressId, customerId, receiverAddress, receiverName, receiverPhone, province, ward, district, isDefault);
                Order order = new Order(orderId, customerId, paymentMethod, status, time, address);
                Shop shop = new Shop(shopId, shopName, shopAddress, description, shopStatus);

                JsonArray itemsJsonArray = orderJsonObject.get("items").getAsJsonArray();
                for (JsonElement itemJsonElement : itemsJsonArray) {
                    JsonObject itemJsonObject = itemJsonElement.getAsJsonObject();

                    int productId = Integer.parseInt(itemJsonObject.get("productId").getAsString());
                    String name = itemJsonObject.get("name").getAsString();
                    int price = Integer.parseInt(itemJsonObject.get("price").getAsString());
                    int quantity = Integer.parseInt(itemJsonObject.get("quantity").getAsString());
                    String itemDescription = itemJsonObject.get("description").getAsString();
                    int orderQuantity = Integer.parseInt(itemJsonObject.get("orderQuantity").getAsString());
                    boolean itemIsAvailable = itemJsonObject.get("is_available").getAsBoolean();
                    // Category
                    JsonObject categoryJsonObject = itemJsonObject.get("category").getAsJsonObject();
                    int categoryId = Integer.parseInt(categoryJsonObject.get("category_id").getAsString());
                    String categoryName = categoryJsonObject.get("category_name").getAsString();
                    
                    Category category = new Category(categoryId, categoryName);
                    Product product = new Product(productId, name, price, quantity, itemDescription, shop, category, itemIsAvailable);
                    items.put(product, orderQuantity);
                }
                orders.put(order, items);
                
            }
            return orders;
        }
        
    }
    
}
