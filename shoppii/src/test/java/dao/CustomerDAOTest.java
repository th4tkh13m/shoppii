package dao;

import static org.junit.Assert.assertEquals;

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
import junit.framework.AssertionFailedError;
import model.Customer;
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
        System.out.println("DATABASE Created Succesfully.");


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
            Customer customer = CustomerDAO.createCustomer(1, "An", "an@gmail.com", "0123456789",
                    Date.valueOf("2002-10-10"), true, "abc123");

            CustomerDAO.insertCustomer(customer, connection);
    }

    @Test
    public void testGetCustomer() {
        Customer customer = CustomerDAO.createCustomer(1, "An", "an@gmail.com", "0123456789",
                    Date.valueOf("2002-10-10"), true, "abc123");
        CustomerDAO.insertCustomer(customer, connection);
        assertEquals(customer, CustomerDAO.getCustomerFromId(1, connection));
    }

    @After
    public void tearDown() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP DATABASE " + dbName);
        System.out.println("DATABASE Dropped Succesfully.");
        connection.close();
    }
}
