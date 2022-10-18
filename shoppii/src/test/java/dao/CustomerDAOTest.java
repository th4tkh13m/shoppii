package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import utils.DBInfo;
import utils.Utils;

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
    
    String code = Utils.generateCode();
    
    @Test
    public void testInsertCustomer() {
        System.out.println("TEST: Customer Insert.");
        Customer customer = CustomerDAO.createCustomer("An", "an@gmail.com", "0123456789", "abc123", code);

        try {
            assertTrue(CustomerDAO.insertCustomer(customer, connection));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        System.out.println("Test passed.");
        
    }

    @Test
    public void testGetCustomer() {
        System.out.println("TEST: Get Customer.");
        Customer customer = CustomerDAO.createCustomer("An", "an@gmail.com", "0123456789", "abc123", code);
        try {
            CustomerDAO.insertCustomer(customer, connection);
            assertEquals(customer, CustomerDAO.getCustomerFromId(1, connection));
        System.out.println("Test passed.");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        
    }

    @Test
    public void testUpdateCustomer() {
        System.out.println("TEST: Update Customer Information.");
        
        Customer customer;
        try {
            customer = CustomerDAO.register("0123456789", "abc123", code, connection);
            customer.setName("Binh");
        customer.setMail("binh@gmail.com");
        Customer updatedCustomer = CustomerDAO.updateInfo(customer, connection, null, null);
        assertEquals(customer, updatedCustomer);
        System.out.println("Test passed.");
        } catch (SQLException | AwsServiceException | SdkClientException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        
    }

   
    @Test
    public void checkLoginByMailTest1() {
        try {
            CustomerDAO.register("0123456789", "abc123", code, connection);
            assertNotNull(CustomerDAO.checkLogin("an@gmail.com", null, "abc123", connection));
        } catch (AssertionError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Assertion Error");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        
    }

    @Test
    public void checkLoginByMailTest2() {
        try {
            CustomerDAO.register("0123456789", "abc123", code, connection);
            CustomerDAO.checkLogin("an@gmail.com", null, "abc1", connection);
            System.out.println(CustomerDAO.checkLogin("an@gmail.com", null, "abc1", connection));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        } catch (Exception e1) {

        }

        
    }
    
    @Test
    public void checkLoginByPhoneTest1() {
        try {
            CustomerDAO.register("0123456789", "abc123", code, connection);
            CustomerDAO.checkLogin(null, "0123456789", "abc1", connection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        } catch (Exception e1) {

        }

        
    }

    @Test
    public void checkLoginByPhoneTest2() {
        
        try {
            CustomerDAO.register("0123456789", "abc123", code, connection);
            assertNotNull(CustomerDAO.checkLogin(null, "0123456789", "abc123", connection));
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
