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
}
