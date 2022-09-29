package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.password4j.Argon2Function;
import com.password4j.types.Argon2;

import dbconnect.DBConnect;
import dbconnect.S3Util;
import model.Customer;

public class CustomerDAO {
    private static int memory = 2048;
    private static int iterations = 10;
    private static int parallelism = 1;
    private static int outputLength = 128;
    private static Argon2 variant = Argon2.I;

    private static Argon2Function argon2 = Argon2Function.getInstance(memory, iterations, parallelism, outputLength, variant);


    public static Customer getCustomerFromId(int customerId, Connection connection) {
        Customer customer = null;
        try {
            String sql = "SELECT name, mail, phone, dob, sex, password FROM `Customer` WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String name = result.getString(1);
                String mail = result.getString(2);
                String phone = result.getString(3);
                Date dob = result.getDate(4);
                boolean sex = result.getBoolean(5);
                String password = result.getString(6);
                customer = new Customer(customerId, name, mail, phone, dob, sex, password);
            }
            return customer;
        } catch (SQLException e) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }   
    }

    // Wrapper function for easy customer initialization
    public static Customer createCustomer(int customerId, String name, String mail,
                                        String phone, Date dob, boolean sex, String password) {
        return new Customer(customerId, name, mail, phone, dob, sex, password, argon2);
    }

    /**
     * Use to Insert a Customer information to Database and Amazon S3.
     * @param customer Customer to insert into DB
     * @param fileName Avatar image's filename. Default to null if not uploaded.
     * @param avatar InputStream of avatar image.
     * @param connection DB Connection.
     * @return
     * true if the operation success, false otherwise.
     */
    public static boolean insertCustomer(Customer customer, String fileName, InputStream avatar, Connection connection) {
        try {
            String sql = "INSERT INTO `Customer` VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, customer.getUserId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getMail());
            statement.setString(4, customer.getPhone());
            statement.setDate(5, customer.getDob());
            statement.setBoolean(6, customer.getSex());
            statement.setString(7, customer.getEncryptedPassword());

            statement.execute();
            
            if (avatar != null) {
                S3Util.uploadObject("profile/" + customer.getUserId() +
                                    "/user/avatar/" + fileName, avatar);
            }
            return true;
            
        } catch (Exception e) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    /**
     * @param newCustomer Updated version of the customer object.
     * @param connection Connection to the database.
     * @return
     */
    public static Customer updateInfo(Customer newCustomer, Connection connection) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE `Customer` SET name = ?," +
                                    "mail = ?," +
                                    "phone = ?," +
                                    "dob = ?," +
                                    "sex = ?," +
                                    "password = ? " +
                                    "WHERE user_id = ?");
            statement.setString(1, newCustomer.getName());
            statement.setString(2, newCustomer.getMail());
            statement.setString(3, newCustomer.getPhone());
            statement.setDate(4, newCustomer.getDob());
            statement.setBoolean(5, newCustomer.getSex());
            statement.setString(6, newCustomer.getEncryptedPassword());
            statement.setInt(7, newCustomer.getUserId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getCustomerFromId(newCustomer.getUserId(), connection);
    }

    public static boolean createRequest(Customer customer, Connection connection) {
        return true;
    }

    
}
