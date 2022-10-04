package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.password4j.Argon2Function;
import com.password4j.types.Argon2;

import dbconnect.DBConnect;
import dbconnect.S3Util;
import model.Customer;
import model.ShopRequest;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import utils.Utils;

public class CustomerDAO {
    private static int memory = 2048;
    private static int iterations = 10;
    private static int parallelism = 1;
    private static int outputLength = 128;
    private static Argon2 variant = Argon2.I;

    private static Argon2Function argon2 = Argon2Function.getInstance(memory, iterations, parallelism, outputLength,
            variant);

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

    protected static Customer createCustomer(String name, String mail,
            String phone, String password) {
        return new Customer(name, mail, phone, password, argon2);
    }

    /**
     * Use to Insert a Customer information to Database and Amazon S3.
     * 
     * @param customer   Customer to insert into DB
     * @param fileName   Avatar image's filename. Default to null if not uploaded.
     * @param avatar     InputStream of avatar image.
     * @param connection DB Connection.
     * @return
     *         true if the operation success, false otherwise.
     */
    protected static boolean insertCustomer(Customer customer, Connection connection) {
        try {
            String sql = "INSERT INTO `Customer` (name, mail, phone, `password`) VALUES " +
                    "(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getMail());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getEncryptedPassword());

            statement.execute();

            return true;

        } catch (Exception e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    /**
     * @param newCustomer Updated version of the customer object.
     * @param connection  Connection to the database.
     * @return
     */
    public static Customer updateInfo(Customer newCustomer, Connection connection, String fileName,
            InputStream avatar) {
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
            if (avatar != null) {
                S3Util.uploadObject("profile/" + newCustomer.getUserId() +
                        "/user/avatar/" + fileName, avatar);
            }
        } catch (SQLException | AwsServiceException | SdkClientException | IOException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getCustomerFromId(newCustomer.getUserId(), connection);
    }

    public static ArrayList<ShopRequest> getRequests(Customer customer, Connection connection) {
        try {
            ArrayList<ShopRequest> requests = new ArrayList<>();
            String sql = "SELECT name, address, description, status, time FROM `ShopRequests` WHERE customer_id = ? ORDER BY time ASC";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getUserId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String name = result.getString(1);
                String address = result.getString(2);
                String description = result.getString(3);
                String status = result.getString(4);
                Time time = result.getTime(5);

                requests.add(new ShopRequest(customer, name, address, description, status, time));
            }
            return requests;
        } catch (SQLException e) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public static boolean createRequest(Customer customer, String shopName,
            String address, String description, Connection connection) {
        try {
            String sql = "INSERT INTO ShopRequests(customer_id, name, address, description) VALUES " +
                    "(?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getUserId());
            statement.setString(2, shopName);
            statement.setString(3, address);
            statement.setString(4, description);

            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private static Customer getCustomerFromMailOrPhone(String enteredMail, String enteredPhone, Connection connection) {
        Customer customer = null;
        try {
            String sql = "SELECT user_id, name, mail, phone, dob, sex, password FROM `Customer` WHERE mail = ? OR phone = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, enteredMail);
            statement.setString(2, enteredPhone);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int customerId = result.getInt(1);
                String name = result.getString(2);
                String mail = result.getString(3);
                String phone = result.getString(4);
                Date dob = result.getDate(5);
                boolean sex = result.getBoolean(6);
                String password = result.getString(7);
                customer = new Customer(customerId, name, mail, phone, dob, sex, password);
            }
            return customer;
        } catch (SQLException e) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public static Customer register(String mail, String phone, String password, Connection connection) {
        Customer customer = createCustomer(Utils.generateName(), mail, phone, password);
        System.out.println(customer);
        insertCustomer(customer, connection);
        return CustomerDAO.getCustomerFromMailOrPhone(mail, phone, connection);

    }

    public static boolean checkLogin(String enteredMail, String enteredPhone, String enteredPassword,
            Connection connection) {
        Customer customer = getCustomerFromMailOrPhone(enteredMail, enteredPhone, connection);
        return customer.verifyPassword(enteredPassword);
    }
}
