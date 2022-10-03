package dao;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import dbconnect.DBConnect;
import model.Customer;
import model.ShopRequest;
import utils.DBInfo;

@RunWith (MockitoJUnitRunner.class)
public class CustomerDAOTest extends DBInfo {   
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
    
    @Test
    public void testInsertCustomer() {
        System.out.println("TEST: Customer Insert.");
        Customer customer = CustomerDAO.createCustomer("An", "an@gmail.com", "0123456789", "abc123");

        assertTrue(CustomerDAO.insertCustomer(customer, connection));
        System.out.println("Test passed.");
        
    }

    @Test
    public void testGetCustomer() {
        System.out.println("TEST: Get Customer.");
        Customer customer = CustomerDAO.createCustomer("An", "an@gmail.com", "0123456789", "abc123");
        CustomerDAO.insertCustomer(customer, connection);
        assertEquals(customer, CustomerDAO.getCustomerFromId(1, connection));
        System.out.println("Test passed.");
    }

    @Test
    public void testUpdateCustomer() {
        System.out.println("TEST: Update Customer Information.");
        
        Customer customer = CustomerDAO.register("an@gmail.com", null, "abc123", connection);
        customer.setName("Binh");
        customer.setMail("binh@gmail.com");
        Customer updatedCustomer = CustomerDAO.updateInfo(customer, connection, null, null);
        assertEquals(customer, updatedCustomer);
        System.out.println("Test passed.");
    }

   
    @Test
    public void checkLoginByMailTest1() {
        CustomerDAO.register("an@gmail.com", null, "abc123", connection);

        assertTrue(CustomerDAO.checkLogin("an@gmail.com", null, "abc123", connection));
    }

    @Test
    public void checkLoginByMailTest2() {
        CustomerDAO.register("an@gmail.com", null, "abc123", connection);

        assertTrue(!CustomerDAO.checkLogin("an@gmail.com", null, "abc1", connection));
    }
    
    @Test
    public void checkLoginByPhoneTest1() {
        CustomerDAO.register(null, "0123456789", "abc123", connection);

        assertTrue(!CustomerDAO.checkLogin(null, "0123456789", "abc1", connection));
    }

    @Test
    public void checkLoginByPhoneTest2() {
        
        CustomerDAO.register(null, "0123456789", "abc123", connection);

        assertTrue(CustomerDAO.checkLogin(null, "0123456789", "abc123", connection));
    }

    @After
    public void tearDown() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP DATABASE " + dbName);
        connection.close();
    }
}
