package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.password4j.Argon2Function;
import com.password4j.types.Argon2;

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

    public static Customer getCustomerFromId(int customerId, Connection connection) throws SQLException {
        Customer customer = null;
        String sql = "SELECT name, mail, phone, dob, sex, `password`, security_code FROM `Customer` WHERE user_id = ?";
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
            String code = result.getString(7);
            customer = new Customer(customerId, name, mail, phone, dob, sex, password, code);
        }
        return customer;
    }

    public static Customer getCustomerFromIdWithoutPass(int customerId, Connection connection) throws SQLException {
        Customer customer = null;
        String sql = "SELECT name, mail, phone, dob, sex FROM `Customer` WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, customerId);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String name = result.getString(1);
            String mail = result.getString(2);
            String phone = result.getString(3);
            Date dob = result.getDate(4);
            boolean sex = result.getBoolean(5);
            customer = new Customer(customerId, name, mail, phone, dob, sex);
        }
        return customer;
    }

    protected static Customer createCustomer(String name, String mail,
            String phone, String password, String code) {
        return new Customer(name, mail, phone, password, code, argon2);
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
        String sql = "INSERT INTO `Customer` (name, mail, phone, `password`, security_code) VALUES " +
                "(?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, customer.getName());
        statement.setString(2, customer.getMail());
        statement.setString(3, customer.getPhone());
        statement.setString(4, customer.getEncryptedPassword());
        statement.setString(5, customer.getEncryptedCode());
        statement.execute();

        return true;
    }

    public static Customer updateInfo(Customer newCustomer, Connection connection, String fileName, InputStream avatar)
            throws SQLException, S3Exception, AwsServiceException, SdkClientException, IOException {
        PreparedStatement statement = connection
                .prepareStatement("UPDATE `Customer` SET name = ?," +
                        "mail = ?," +
                        "phone = ?," +
                        "dob = ?," +
                        "sex = ? " +
                        "WHERE user_id = ?");
        statement.setString(1, newCustomer.getName());
        statement.setString(2, newCustomer.getMail());
        statement.setString(3, newCustomer.getPhone());
        statement.setDate(4, newCustomer.getDob());
        statement.setBoolean(5, newCustomer.getSex());
        statement.setInt(6, newCustomer.getUserId());
        statement.executeUpdate();
        if (avatar != null) {
            S3Util.uploadObject("profile/" + newCustomer.getUserId() +
                    "/user/avatar/" + fileName, avatar);
        }

        return getCustomerFromId(newCustomer.getUserId(), connection);
    }

    private static Customer getCustomerFromMailOrPhone(String enteredMail, String enteredPhone, Connection connection)
            throws SQLException {
        Customer customer = null;
        String sql = "SELECT user_id, name, mail, phone, dob, sex, `password`, security_code FROM `Customer` WHERE mail = ? OR phone = ?";
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
            String code = result.getString(8);
            customer = new Customer(customerId, name, mail, phone, dob, sex, password, code);
        }
        return customer;

    }

    public static Customer register(String phone, String mail, String password, String code, Connection connection)
            throws SQLException {
        Customer customer = null;
        if (phone == null) {
            customer = createCustomer(Utils.generateName(), mail, null, password, code);
        }
        if (mail == null) {
            customer = createCustomer(Utils.generateName(), null, phone, password, code);
        } 
        System.out.println(customer);
        insertCustomer(customer, connection);
        return CustomerDAO.getCustomerFromMailOrPhone(mail, phone, connection);

    }

    public static Customer checkLogin(String enteredMail, String enteredPhone, String enteredPassword,
            Connection connection) throws Exception {
        Customer customer = getCustomerFromMailOrPhone(enteredMail, enteredPhone, connection);
        if (customer.verifyPassword(enteredPassword)) {
            return customer;
        } else {
            throw new Exception();
        }
    }

    public static boolean checkEmailExist(String mail, Connection connection) throws SQLException {
        String sql = "SELECT mail FROM `Customer` WHERE mail = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, mail);
        ResultSet result = statement.executeQuery();
        return result.next();
    }

    public static Customer getCustomerFromMail(String enteredMail, Connection connection)
            throws SQLException {
        Customer customer = null;
        String sql = "SELECT user_id, name, mail, phone, dob, sex, `password`, security_code FROM `Customer` WHERE mail = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, enteredMail);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int customerId = result.getInt(1);
            String name = result.getString(2);
            String mail = result.getString(3);
            String phone = result.getString(4);
            Date dob = result.getDate(5);
            boolean sex = result.getBoolean(6);
            String password = result.getString(7);
            String code = result.getString(8);
            customer = new Customer(customerId, name, mail, phone, dob, sex, password, code);
        }
        return customer;
    }

    public static Customer checkResetPasswordInfo(String email, String phone, String securityCode, Connection connection) throws SQLException {
        Customer customer = getCustomerFromMailOrPhone(email, phone, connection);
        if (customer.verifyCode(securityCode)) {
            return customer;
        }
        return null;
    }

    public static Customer resetPassword(String password, Customer customer, Connection connection) throws S3Exception, AwsServiceException, SdkClientException, SQLException, IOException {
        customer.encryptPassword(password, argon2);
        String sql = "UPDATE Customer SET password = ? WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, customer.getEncryptedPassword());
        statement.setInt(2, customer.getUserId());

        statement.executeUpdate();
        return getCustomerFromId(customer.getUserId(), connection);
    }
}
