package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Address;

public class AddressDAO {

    public static Address getDefaultAddressOfUser(int userId, Connection connection) throws SQLException {
        Address address = null;
        String sql = "SELECT address_id FROM Address WHERE user_id=? AND is_default";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            address = getAddressFromId(result.getInt(1), connection);
        }

        return address;
    }



    public static ArrayList<Address> getAddressOfUser(int userId, Connection connection) throws SQLException {
        ArrayList<Address> addresses = new ArrayList<>();

        String sql = "SELECT address_id, receiver_address, receiver_name, receiver_phone, province, ward, district, is_default FROM `Address` WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, userId);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int addressId = result.getInt(1);
            String receiverAddress = result.getString(2);
            String receiverName = result.getString(3);
            String receiverPhone = result.getString(4);
            String province = result.getString(5);
            String ward = result.getString(6);
            String district = result.getString(7);
            boolean isDefault = result.getBoolean(8);

            addresses.add(new Address(addressId, userId, receiverAddress, receiverName, receiverPhone, province, ward, district, isDefault));
        }
        return addresses;
    }

    public static Address getAddressFromId(int addressId, Connection connection) throws SQLException {
        Address address = null;
        String sql = "SELECT address_id, receiver_address, receiver_name, receiver_phone, province, ward, district, is_default FROM `Address` WHERE address_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, addressId);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int customerId = result.getInt(1);
            String receiverAddress = result.getString(2);
            String receiverName = result.getString(3);
            String receiverPhone = result.getString(4);
            String province = result.getString(5);
            String ward = result.getString(6);
            String district = result.getString(7);
            boolean isDefault = result.getBoolean(8);

            address = new Address(addressId, customerId, receiverAddress, receiverName, receiverPhone, province, ward, district, isDefault);
        }
        return address;
    }

    public static Address addAddress(int userId, String receiverAddress, String receiverName,
            String receiverPhoneString, String province, String ward, String district, Connection connection) throws SQLException {

        String sql = "INSERT INTO `Address` (user_id, receiver_address, receiver_name, receiver_phone, province, ward, district, is_default) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        statement.setString(2, receiverAddress);
        statement.setString(3, receiverName);
        statement.setString(4, receiverPhoneString);
        statement.setString(5, province);
        statement.setString(6, ward);
        statement.setString(7, district);
        if (getDefaultAddressOfUser(userId, connection) != null) {
            statement.setBoolean(8, false);
        } else {
            statement.setBoolean(8, true);
        }
        statement.execute();

        String sql2 = "SELECT MAX(address_id) FROM Address";
        Statement statement2 = connection.createStatement();
        ResultSet result = statement2.executeQuery(sql2);
        int addressId = 0;
        while (result.next()) {
            addressId = result.getInt(1);
        }
        return getAddressFromId(addressId, connection);
    }

    public static Address updateAddress(int addressId, int userId, String receiverAddress, String receiverName,
            String receiverPhone, String province, String ward, String district, boolean isDefault, Connection connection) throws SQLException {
            if (isDefault) {
                Address address = getDefaultAddressOfUser(userId, connection);
                System.out.println(address.getAddressId());
                String sqlRemoveDefault = "UPDATE `Address` SET is_default = false WHERE address_id = ?";
                PreparedStatement statement = connection.prepareStatement(sqlRemoveDefault);
                statement.setInt(1, address.getAddressId());
                statement.executeUpdate();
            }

            String sql = "UPDATE `Address` SET receiver_address = ?, receiver_name=?, receiver_phone=?, province=?, ward=?, district=?,  is_default=? WHERE address_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, receiverAddress);
            statement.setString(2, receiverName);
            statement.setString(3, receiverPhone);
            statement.setString(4, province);
            statement.setString(5, ward);
            statement.setString(6, district);
            statement.setBoolean(7, isDefault);
            statement.setInt(8, addressId);
            
            statement.execute();

            return getAddressFromId(addressId, connection);
    }

    public static boolean deleteAddress(int addressId, int userId, Connection connection) throws SQLException {
        String sql = "DELETE FROM `Address` WHERE address_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, addressId);
        statement.setInt(2, userId);
        statement.execute();

        return true;
    }
}
