/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author th4tkh13m
 */
public class DBConnect {

    private Connection connection;

    public DBConnect() {
        try {
            // Get environment variables saved in web.xml
            Context ctx = new InitialContext();
            Context env = (Context)ctx.lookup("java:comp/env");
            String className = (String)env.lookup("className");
            String userName = (String)env.lookup("userName");
            String password = (String)env.lookup("password");
            String dbName = (String)env.lookup("dbName");
            String host = (String)env.lookup("host");

            String url = "jdbc:mysql://" + host + "/" + dbName; 

            // Declare MySQL class name
            Class.forName(className);

            // Create connection to the database
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("LOGIN OK");
        } catch (ClassNotFoundException | SQLException | NamingException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
