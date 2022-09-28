package dbconnect;


import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Statement;
import org.junit.Test;

import utils.DBInfo;

public class DBConnectTest extends DBInfo{

    private static String dbName = "TESTDB";

    @Test
    public void testCreateConnection() { 
        try {
            DBConnect dbConnect = new DBConnect(className, sqlType, host, userName, password);
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
