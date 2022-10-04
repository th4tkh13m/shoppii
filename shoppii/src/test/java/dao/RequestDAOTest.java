package dao;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import dbconnect.DBConnect;
import model.Customer;
import model.ShopRequest;
import utils.DBInfo;
@RunWith (MockitoJUnitRunner.class)
public class RequestDAOTest extends DBInfo{
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
    public void createRequestTest() {
        System.out.println("TEST: Create new Shop Request.");
        Customer customer;
        try {
            customer = CustomerDAO.register("an@gmail.com", null, "abc123", connection);
            assertNotNull(RequestDAO
            .createRequest(customer.getUserId(), "Apple", "US", "Sell overpriced things", connection));
        System.out.println("Test passed.");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        
    }

    @Test
    public void getRequestsTest() {
        System.out.println("TEST: Get Shop Requests list.");
        Customer customer;
        try {
            customer = CustomerDAO.register("an@gmail.com", null, "abc123", connection);
            RequestDAO
            .createRequest(customer.getUserId(), "Apple", "US", "Sell overpriced things", connection);
            Thread.sleep(1000);

        // Use to check order of received requests.
        RequestDAO
            .createRequest(customer.getUserId(), "HP", "US", "Sell normal things", connection);
        ArrayList<String> names = new ArrayList<>();
        
        for (ShopRequest request : RequestDAO.getRequests(customer.getUserId(), connection)) {
            names.add(request.getName());
        }

        String[] expected = {"Apple", "HP"};
        assertArrayEquals(expected, names.toArray());
        System.out.println("Test Passed.");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            fail();
        }
        
       

        
    }


    @Test
    public void testAcceptRequest() {

    }

    @Test
    public void testRejectRequest() {

    }
    @After
    public void tearDown() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP DATABASE " + dbName);
        connection.close();
    }
}
