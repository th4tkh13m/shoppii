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
        String json = "[{\"shopId\":2,\"shopName\":\"NiceHCK Official Store\",\"address\":\"China\",\"description\":\"Sell audio accessories\",\"status\":true,\"products\":[{\"productId\":1,\"productName\":\"Nicehck EB2S 3.5mm Metal CNC HIFI Earbud 15.4mm LCP Nhạc động Âm nhạc giọng hát Tai nghe có dây Tai nghe HD B40 / B70 / EBX21\",\"price\":320000,\"quantity\":2}]}]";
        System.out.println(json);
        HashMap<Shop, HashMap<Product, Integer>> test = gson.fromJson(json, type);
        System.out.println(test);
    }
}
