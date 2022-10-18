package utils;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import model.Product;
import model.Shop;

import java.lang.reflect.Type;
import java.util.HashMap;

import org.junit.Test;

public class ProductMapTest {
    GsonBuilder gsonBuilder = new GsonBuilder();
    Type type = new TypeToken<HashMap<Shop, HashMap<Product, Integer>>>() {}.getType();
    
    @Test
    public void derializeTest() {
        gsonBuilder.registerTypeAdapter(type, new ProductMap());
        Gson gson = gsonBuilder.create();
        String json = "{\"shops\": [{\"shopId\": 2,\"shopName\": \"Apple\",\"address\": \"US\",\"description\": \"Sell overpriced things\",\"status\":false,\"products\": [{\"productId\": 2,\"productName\": \"Iphone 16\",\"price\": 1000,\"quantity\": 2},{\"productId\": 1,\"productName\": \"Iphone \",\"price\": 1000,\"quantity\": 5}]}]}";
        HashMap<Shop, HashMap<Product, Integer>> test = gson.fromJson(json, type);
        System.out.println(test);
    }
}
