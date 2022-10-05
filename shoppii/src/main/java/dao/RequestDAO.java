package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dbconnect.DBConnect;
import model.Customer;
import model.Shop;
import model.ShopRequest;

public class RequestDAO {

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
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, e);
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
    private static boolean changeStatus(int customerId, String status, Connection connection) {
        try {
            String sql = "UPDATE `ShopRequests` SET status = ? WHERE customer_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, customerId);

            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public static boolean acceptRequest(Shop shop, Connection connection) throws SQLException {
        changeStatus(shop.getShopId(), "Accepted", connection);
        
        String sql = "insert into Shop (shop_id, name, address, description) values (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, shop.getShopId());
        statement.setString(2, shop.getName());
        statement.setString(3, shop.getAddress());
        statement.setString(4, shop.getDescription());
        statement.executeUpdate();
        return true;
    }

    public static boolean rejectRequest(int orderId, Connection connection) {
        return changeStatus(orderId, "Rejected", connection);
    }

}
