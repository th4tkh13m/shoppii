package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import dbconnect.DBConnect;
import model.Customer;
import model.Product;
import model.Shop;
import utils.DBInfo;
import utils.Utils;

@RunWith (MockitoJUnitRunner.class)
public class CartDAOTest extends DBInfo {
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

    String code = Utils.generateCode();

    @Test
    public void testGetCartOfCustomer() {
        try {
            
            Customer customer = CustomerDAO.register("0123456789", null, "abc123", code, connection);
            // create request
            assertNotNull(RequestDAO
            .createRequest(customer.getUserId(), "Apple", "US", "Sell overpriced things", connection));
            
            RequestDAO.acceptRequest(1, connection);

            // create product
            Product product1 = new Product(1,"iphone14", 1, 999, "lol", CustomerDAO.getShopFromId(2, connection), CategoryDAO.getCategoryFromId(2, connection));
            Product product2 = new Product(2, "iphone15", 1, 999, "nice", CustomerDAO.getShopFromId(2, connection), CategoryDAO.getCategoryFromId(2, connection));
            ProductDAO.addProduct(product1, connection);
            ProductDAO.addProduct(product2, connection);
            
            String sql = "INSERT INTO Cart VALUES (1,1,1)";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            sql = "INSERT INTO Cart VALUES (1,2,1)";
            statement.execute(sql);

            HashMap<Shop, HashMap<Product, Integer>> cart = CartDAO.getCartOfCustomer(1, connection);
            System.out.println(cart);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testAddProductToCart() {
        try {
            Customer customer = CustomerDAO.register("0123456789", null, "abc123", code, connection);
            // create request
            assertNotNull(RequestDAO
            .createRequest(customer.getUserId(), "Apple", "US", "Sell overpriced things", connection));
            
            RequestDAO.acceptRequest(1, connection);

            // create product
            Product product1 = new Product(1,"iphone14", 1, 999, "lol", CustomerDAO.getShopFromId(2, connection), CategoryDAO.getCategoryFromId(2, connection));
            Product product2 = new Product(2, "iphone15", 1, 999, "nice", CustomerDAO.getShopFromId(2, connection), CategoryDAO.getCategoryFromId(2, connection));
            ProductDAO.addProduct(product2, connection);

            CartDAO.addProductToCart(1, 1, 1, connection);
            CartDAO.addProductToCart(1, 2, 1, connection);
            CartDAO.addProductToCart(1, 2, 3, connection);
            HashMap<Shop, HashMap<Product, Integer>> cart = CartDAO.getCartOfCustomer(1, connection);
            System.out.println(cart);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
            
    }

    @Test
    public void testDeleteProductFromCart() {
        try {
            Customer customer = CustomerDAO.register("0123456789", null, "abc123", code, connection);
            // create request
            assertNotNull(RequestDAO
            .createRequest(customer.getUserId(), "Apple", "US", "Sell overpriced things", connection));
            
            RequestDAO.acceptRequest(1, connection);

            // create product
            Product product1 = new Product(1,"iphone14", 1, 999, "lol", CustomerDAO.getShopFromId(2, connection), CategoryDAO.getCategoryFromId(2, connection));
            Product product2 = new Product(2, "iphone15", 1, 999, "nice", CustomerDAO.getShopFromId(2, connection), CategoryDAO.getCategoryFromId(2, connection));
            ProductDAO.addProduct(product1, connection);
            ProductDAO.addProduct(product2, connection);

            CartDAO.addProductToCart(1, 1, 1, connection);
            CartDAO.addProductToCart(1, 2, 1, connection);
            CartDAO.addProductToCart(1, 2, 3, connection);

            CartDAO.deleteProductFromCart(1, 1, connection);
            CartDAO.deleteProductFromCart(1, 2, connection);
            HashMap<Shop, HashMap<Product, Integer>> cart = CartDAO.getCartOfCustomer(1, connection);
            assertEquals(new HashMap<>(), cart);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testModifyProductQuantity1() {
        try {
            Customer customer = CustomerDAO.register("0123456789", null, "abc123", code, connection);
            // create request
            assertNotNull(RequestDAO
            .createRequest(customer.getUserId(), "Apple", "US", "Sell overpriced things", connection));
            
            RequestDAO.acceptRequest(1, connection);

            // create product
            Product product1 = new Product(1,"iphone14", 1, 999, "lol", CustomerDAO.getShopFromId(2, connection), CategoryDAO.getCategoryFromId(2, connection));
            Product product2 = new Product(2, "iphone15", 1, 999, "nice", CustomerDAO.getShopFromId(2, connection), CategoryDAO.getCategoryFromId(2, connection));
            ProductDAO.addProduct(product1, connection);
            ProductDAO.addProduct(product2, connection);

            CartDAO.addProductToCart(1, 1, 1, connection);
            CartDAO.addProductToCart(1, 2, 1, connection);
            CartDAO.addProductToCart(1, 2, 3, connection);

            CartDAO.modifyProductQuantity(1, 2, -1, connection);
            HashMap<Shop, HashMap<Product, Integer>> cart = CartDAO.getCartOfCustomer(1, connection);
            assertNotEquals(new HashMap<>(), cart);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    

    @After
    public void tearDown() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP DATABASE " + dbName);
        connection.close();
    }

}
