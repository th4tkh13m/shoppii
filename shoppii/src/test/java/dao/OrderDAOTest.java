package dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dbconnect.DBConnect;
import model.Order;
import model.Product;
import model.Shop;
import utils.DBInfo;
import utils.ProductMap;

@RunWith (MockitoJUnitRunner.class)
public class OrderDAOTest extends DBInfo {
    @InjectMocks
    private static String dbName = "TESTDB";
    DBConnect db;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement statement;

    @Before
    public void setup() throws ClassNotFoundException, SQLException, FileNotFoundException {
        db = new DBConnect(className, sqlType, host, userName, password);
        connection = db.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE " + dbName);


        db = new DBConnect(className, sqlType, host, dbName, userName, password);
        connection = db.getConnection();
        ScriptRunner sr = new ScriptRunner(connection);
        ClassLoader classLoader = this.getClass().getClassLoader();
        File tables = new File(classLoader.getResource("sql/tables.sql").getFile());
        Reader reader = new BufferedReader(new FileReader(tables));
        sr.setLogWriter(null);
        sr.runScript(reader);
    }

    GsonBuilder gsonBuilder = new GsonBuilder();
    Type type = new TypeToken<HashMap<Shop, HashMap<Product, Integer>>>() {}.getType();
    

    @Test
    public void testInsertOrder() {
        System.out.println("TEST: Order Insert.");
        gsonBuilder.registerTypeAdapter(type, new ProductMap());
        Gson gson = gsonBuilder.create();

        try {
            AddressDAO.addAddress(3, "VN", "DAO", "113", connection);
        
        // 
        HashMap<Shop, HashMap<Product, Integer>> cart = CartDAO.getCartOfCustomer(3, connection);
        String cartJson = "{\"shops\":[{\"shopId\":2,\"shopName\":\"NiceHCK Official Store\",\"address\":\"China\",\"description\":\"Sell audio accessories\",\"status\":true,\"products\":[{\"productId\":1,\"productName\":\"Nicehck EB2S 3.5mm Metal CNC HIFI Earbud 15.4mm LCP Nhạc động Âm nhạc giọng hát Tai nghe có dây Tai nghe HD B40 / B70 / EBX21\",\"price\":320000,\"quantity\":2}]}]}";
        System.out.println(cartJson);

        HashMap<Shop, HashMap<Product, Integer>> order = gson.fromJson(cartJson, type);
        ArrayList<Order> orders = OrderDAO.addOrder(order, 3, cartJson, 1, connection);
        System.out.println(orders);
            

            assertNotNull(OrderDAO.getOrderFromId(1, connection));
            System.out.println(OrderDAO.getOrderFromId(1, connection));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        System.out.println("Test passed.");
        
    }

    @Test
    public void testGetOrderShop() {
        System.out.println("TEST: Order Get Shop.");
        gsonBuilder.registerTypeAdapter(type, new ProductMap());
        Gson gson = gsonBuilder.create();

        try {
            AddressDAO.addAddress(3, "VN", "DAO", "113", connection);
        
        // 
        HashMap<Shop, HashMap<Product, Integer>> cart = CartDAO.getCartOfCustomer(3, connection);
        String cartJson = "{\"shops\":[{\"shopId\":2,\"shopName\":\"NiceHCK Official Store\",\"address\":\"China\",\"description\":\"Sell audio accessories\",\"status\":true,\"products\":[{\"productId\":1,\"productName\":\"Nicehck EB2S 3.5mm Metal CNC HIFI Earbud 15.4mm LCP Nhạc động Âm nhạc giọng hát Tai nghe có dây Tai nghe HD B40 / B70 / EBX21\",\"price\":320000,\"quantity\":2}]}]}";
        System.out.println(cartJson);

        HashMap<Shop, HashMap<Product, Integer>> order = gson.fromJson(cartJson, type);
        ArrayList<Order> orders = OrderDAO.addOrder(order, 3, cartJson, 1, connection);
        System.out.println(orders);
            

            assertNotNull(OrderDAO.getOrdersByShop(ShopDAO.getShopFromId(2, connection), "Pending", connection));
            System.out.println(OrderDAO.getOrdersByShop(ShopDAO.getShopFromId(2, connection), "Pending", connection));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        System.out.println("Test passed.");
        
    }

    @Test
    public void testAccept() {
        System.out.println("TEST: Order Accept.");
        gsonBuilder.registerTypeAdapter(type, new ProductMap());
        Gson gson = gsonBuilder.create();

        try {
            AddressDAO.addAddress(3, "VN", "DAO", "113", connection);
        
        // 
        HashMap<Shop, HashMap<Product, Integer>> cart = CartDAO.getCartOfCustomer(3, connection);
        String cartJson = "{\"shops\":[{\"shopId\":2,\"shopName\":\"NiceHCK Official Store\",\"address\":\"China\",\"description\":\"Sell audio accessories\",\"status\":true,\"products\":[{\"productId\":1,\"productName\":\"Nicehck EB2S 3.5mm Metal CNC HIFI Earbud 15.4mm LCP Nhạc động Âm nhạc giọng hát Tai nghe có dây Tai nghe HD B40 / B70 / EBX21\",\"price\":320000,\"quantity\":2}]}]}";
        System.out.println(cartJson);

        HashMap<Shop, HashMap<Product, Integer>> order = gson.fromJson(cartJson, type);
        ArrayList<Order> orders = OrderDAO.addOrder(order, 3, cartJson, 1, connection);
        System.out.println(orders);
        Order order2 = OrderDAO.acceptOrder(1, connection);
            

            assertNotNull(order2);
            System.out.println(order2);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        System.out.println("Test passed.");
        
    }

    @After
    public void tearDown() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP DATABASE " + dbName);
        connection.close();
    }
    
}
