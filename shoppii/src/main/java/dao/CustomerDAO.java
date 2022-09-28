package dao;

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

    public static void insertCustomer(Customer customer, Connection connection) {
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
        } catch (Exception e) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
