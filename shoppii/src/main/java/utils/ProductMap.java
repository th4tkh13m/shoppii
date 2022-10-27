package utils;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;

import model.Product;
import model.Shop;

public class ProductMap implements JsonSerializer<HashMap<Shop, HashMap<Product, Integer>>> , JsonDeserializer<HashMap<Shop, HashMap<Product, Integer>>> {

    @Override
    public JsonElement serialize(HashMap<Shop, HashMap<Product, Integer>> src, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonArray shopList = new JsonArray();
        for (Shop shop : src.keySet()) {
            JsonObject shopJson = new JsonObject();
            shopJson.addProperty("shopId", shop.getShopId());
            shopJson.addProperty("shopName", shop.getName());
            shopJson.addProperty("address", shop.getAddress());
            shopJson.addProperty("description", shop.getDescription());
            shopJson.addProperty("status", shop.isStatus());
            JsonArray productList = new JsonArray();

            for (Product product : src.get(shop).keySet()) {
                JsonObject productJson = new JsonObject();
                productJson.addProperty("productId", product.getProductId());
                productJson.addProperty("productName", product.getName());
                productJson.addProperty("price", product.getPrice());
                productJson.addProperty("quantity", src.get(shop).get(product));
                productList.add(productJson);
            }
            shopJson.add("products", productList);
            shopList.add(shopJson);
        }

        
        return shopList;
    }

    @Override
    public HashMap<Shop, HashMap<Product, Integer>> deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonNull()) {
            return null;
        } else {

            HashMap<Shop, HashMap<Product, Integer>> orders = new HashMap<>();
            JsonArray shops = json.getAsJsonArray();

            for (JsonElement shopElement : shops) {
                JsonObject shopObj = shopElement.getAsJsonObject();

                Shop shop = new Shop(shopObj.get("shopId").getAsInt(),
                shopObj.get("shopName").getAsString(), 
                shopObj.get("address").getAsString(), 
                shopObj.get("description").getAsString(),
                shopObj.get("status").getAsBoolean());

                HashMap<Product, Integer> products = new HashMap<>();

                for (JsonElement productElement : shopObj.get("products").getAsJsonArray()) {
                    JsonObject productObj = productElement.getAsJsonObject();

                    Product product = new Product(productObj.get("productId").getAsInt(),
                    productObj.get("productName").getAsString(), 
                    productObj.get("price").getAsInt());

                    int quantity = productObj.get("quantity").getAsInt();

                    products.put(product, quantity);
                }
                orders.put(shop, products);
            }

            return orders;
        }
    }  
}
