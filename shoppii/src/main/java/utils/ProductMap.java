package utils;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonArray;

import model.Product;
import model.Shop;

public class ProductMap implements JsonSerializer<HashMap<Shop, HashMap<Product, Integer>>> {

    @Override
    public JsonElement serialize(HashMap<Shop, HashMap<Product, Integer>> src, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        JsonArray shopList = new JsonArray();
        for (Shop shop : src.keySet()) {
            JsonObject shopJson = new JsonObject();
            shopJson.addProperty("shopId", shop.getShopId());
            shopJson.addProperty("shopName", shop.getName());
            shopJson.addProperty("address", shop.getAddress());
            shopJson.addProperty("description", shop.getDescription());
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
        obj.add("shops", shopList);
        
        return obj;
    }
    
}
