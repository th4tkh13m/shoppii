package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Address;
import model.Customer;

public class AddressDAO {
    public static ArrayList<Address> getAddressOfUser(Customer customer, Connection connection) {
        ArrayList<Address> addresses = new ArrayList<>();

        try {
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
        } catch (Exception e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
            return addresses;
        }
    }

    public static Address getAddressFromId(int addressId, Connection connection) {
        Address address = null;
        try {
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
        } catch (Exception e) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
            return address;
        }
    }

    public static boolean addAddress(int addressId, int userId, String receiverAddress, String receiverName,
            String receiverPhone, boolean isDefault, Connection connection) {
        try {
            String sql = "INSERT INTO `Address` (address_id, user_id, receiver_address, receiver_name, receiver_phone, is_default) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, addressId);
            statement.setInt(2, userId);
            statement.setString(3, receiverAddress);
            statement.setString(4, receiverName);
            statement.setString(5, receiverPhone);
            statement.setBoolean(6, isDefault);
            statement.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
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
            String receiverPhone, boolean isDefault, Connection connection) {
        try {
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
        } catch (Exception e) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}
