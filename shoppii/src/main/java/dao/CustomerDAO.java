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
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.model.S3Exception;
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
     * @throws SQLException
     */
    protected static boolean insertCustomer(Customer customer, Connection connection) throws SQLException {
            String sql = "INSERT INTO `Customer` (name, mail, phone, `password`) VALUES " +
                    "(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getMail());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getEncryptedPassword());

            statement.execute();

            return true;
    }

    /**
     * @param newCustomer Updated version of the customer object.
     * @param connection  Connection to the database.
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws SdkClientException
     * @throws AwsServiceException
     * @throws S3Exception
     */
<<<<<<< HEAD
    public static Customer updateInfo(Customer newCustomer, Connection connection, String fileName,
            InputStream avatar) {
        try {
=======
    public static Customer updateInfo(Customer newCustomer, Connection connection, String fileName, InputStream avatar) throws SQLException, S3Exception, AwsServiceException, SdkClientException, IOException {
>>>>>>> 7a9b0e5fc07af3640c4eb8fcbb86542f4953e817
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
        
        return getCustomerFromId(newCustomer.getUserId(), connection);
    }

    private static Customer getCustomerFromMailOrPhone(String enteredMail, String enteredPhone, Connection connection) throws SQLException {
        Customer customer = null;
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
        
    }

<<<<<<< HEAD
    public static Customer register(String mail, String phone, String password, Connection connection) {
=======

    public static Customer register(String mail, String phone, String password, Connection connection) throws SQLException {
>>>>>>> 7a9b0e5fc07af3640c4eb8fcbb86542f4953e817
        Customer customer = createCustomer(Utils.generateName(), mail, phone, password);
        System.out.println(customer);
        insertCustomer(customer, connection);
        return CustomerDAO.getCustomerFromMailOrPhone(mail, phone, connection);

    }

<<<<<<< HEAD
    public static boolean checkLogin(String enteredMail, String enteredPhone, String enteredPassword,
            Connection connection) {
=======
    public static Customer checkLogin(String enteredMail, String enteredPhone, String enteredPassword, Connection connection) throws Exception {
>>>>>>> 7a9b0e5fc07af3640c4eb8fcbb86542f4953e817
        Customer customer = getCustomerFromMailOrPhone(enteredMail, enteredPhone, connection);
        if (customer.verifyPassword(enteredPassword)) {
            return customer;
        } else {
            throw new Exception();
        }
    }
}
