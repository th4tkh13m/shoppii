package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Address;
import model.Customer;

public class AddressDAO {
    public static ArrayList<Address> getAddressOfUser(Customer customer, Connection connection) throws SQLException {
        ArrayList<Address> addresses = new ArrayList<>();

            String sql = "SELECT address_id, receiver_address, receiver_name, receiver_phone, is_default FROM `Address` WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, customer.getUserId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int addressId = result.getInt(1);
                String receiverAddress = result.getString(2);
                String receiverName = result.getString(3);
                String receiverPhone = result.getString(4);
                boolean isDefault = result.getBoolean(5);

                addresses.add(new Address(addressId, customer.getUserId(), receiverAddress, receiverName, receiverPhone,
                        isDefault));
            }
            return addresses;
    }

    public static Address getAddressFromId(int addressId, Connection connection) throws SQLException {
        Address address = null;
            String sql = "SELECT user_id, receiver_address, receiver_name, receiver_phone, is_default FROM `Address` WHERE address_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, addressId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int customerId = result.getInt(1);
                String receiverAddress = result.getString(2);
                String receiverName = result.getString(3);
                String receiverPhone = result.getString(4);
                boolean isDefault = result.getBoolean(5);

                address = new Address(addressId, customerId, receiverAddress, receiverName, receiverPhone,
                        isDefault);
            }
            return address;
    }

    public static Address addAddress(int userId, String receiverAddress, String receiverName,
            String receiverPhoneString, Connection connection) throws SQLException {

            String sql = "INSERT INTO `Address` (user_id, receiver_address, receiver_name, receiver_phone) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, receiverAddress);
            statement.setString(3, receiverName);
            statement.setString(4, receiverPhoneString);
            statement.execute();


            String sql2 = "SELECT MAX(address_id) FROM Address";
            Statement statement2 = connection.createStatement();
            ResultSet result = statement2.executeQuery(sql2);
            int addressId = 0;
            while (result.next()) {
                addressId = result.getInt(1);
            }
            return new Address(addressId, userId, receiverAddress, receiverName, receiverPhoneString, false);
    }

    public static boolean updateAddress(int addressId, int userId, String receiverAddress, String receiverName,
            String receiverPhone, boolean isDefault, Connection connection) {
        try {
            String sql = "UPDATE `Address` SET receiver_address = ?, receiver_name=?, receiver_phone=?, is_default=? WHERE address_id = ? and user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(5, addressId);
            statement.setInt(6, userId);
            statement.setString(1, receiverAddress);
            statement.setString(2, receiverName);
            statement.setString(3, receiverPhone);
            statement.setBoolean(4, isDefault);
            statement.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public static boolean deleteAddress(int addressId, int userId, String receiverAddress, String receiverName,
            String receiverPhone, boolean isDefault, Connection connection) throws SQLException {
            String sql = "DELETE `Address` WHERE address_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(5, addressId);
            statement.setInt(6, userId);
            statement.setString(1, receiverAddress);
            statement.setString(2, receiverName);
            statement.setString(3, receiverPhone);
            statement.setBoolean(4, isDefault);
            statement.execute();
            return true;

    }
}
