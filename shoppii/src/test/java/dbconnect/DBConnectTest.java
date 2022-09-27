package dbconnect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

@FixMethodOrder
public class DBConnectTest {
    private static String className = "com.mysql.cj.jdbc.Driver";
    private static String sqlType = "mysql";
    private static String host = "127.0.0.1";
    private static String dbName = "TESTDB";
    private static String userName = "admin";
    private static String password = "admin";
    

    @Test
    public void testCreateConnection() { 
        try {
            DBConnect dbConnect = new DBConnect(className, sqlType, host, dbName, userName, password);
            Connection connection = dbConnect.getConnection();
        } catch (Exception e) {
            fail("Cannot connect to DB");
        }
    }

    @Test
    public void testCreateDatabase() {
        try {
            DBConnect dbConnect = new DBConnect(className, sqlType, host, dbName, userName, password);
            Connection connection = dbConnect.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE " + dbName);
            System.out.println("DATABASE Created Succesfully.");
            statement.execute("DROP DATABASE " + dbName);
            System.out.println("DATABASE Dropped Succesfully.");
        } catch (Exception e) {
            fail("Cannot create DB: " + dbName);
        }
    }

}
