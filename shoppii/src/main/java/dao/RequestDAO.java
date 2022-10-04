package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dbconnect.DBConnect;
import model.Customer;
import model.Shop;
import model.ShopRequest;

public class RequestDAO {

    public static ArrayList<ShopRequest> getRequests(int customerId, Connection connection) throws SQLException {
            ArrayList<ShopRequest> requests = new ArrayList<>();
            String sql = "SELECT name, address, description, status, time FROM `ShopRequests` WHERE customer_id = ? ORDER BY time ASC";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String name = result.getString(1);
                String address = result.getString(2);
                String description = result.getString(3);
                String status = result.getString(4);
                Time time = result.getTime(5);

                requests.add(new ShopRequest(CustomerDAO.getCustomerFromId(customerId, connection), name, address, description, status, time));
            }
            return requests;
    }

    private static boolean checkUserHasPendingRequest(int customerId, Connection connection) throws SQLException {
        String sql = "SELECT status FROM ShopRequests WHERE customer_id = ? AND status = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, customerId);
        statement.setString(2, "Pending");

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            return true;
        }
        return false;
    }

    public static ShopRequest createRequest(int customerId, String shopName,
            String address, String description, Connection connection) throws Exception {
            ShopRequest request = null;
            if (checkUserHasPendingRequest(customerId, connection)) {
                throw new Exception();
            }
            String sql = "INSERT INTO ShopRequests(customer_id, name, address, description) VALUES " +
                    "(?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setString(2, shopName);
            statement.setString(3, address);
            statement.setString(4, description);

            statement.executeUpdate();
            String sql2 = "SELECT status, time FROM ShopRequests WHERE time = (SELECT MAX(time) FROM ShopRequests)";
            ResultSet result = (connection.createStatement()).executeQuery(sql2);

            while (result.next()) {
                String status = result.getString(1);
                Time time = result.getTime(2);
                request = new ShopRequest(CustomerDAO.getCustomerFromId(customerId, connection), shopName, address, description, status, time);
            }
            return request;

    }
    private static boolean changeStatus(int customerId, String status, Connection connection) throws SQLException {
            String sql = "UPDATE `ShopRequests` SET status = ? WHERE customer_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, customerId);

            statement.executeUpdate();
            return true;
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

    public static boolean rejectRequest(int orderId, Connection connection) throws SQLException {
        return changeStatus(orderId, "Rejected", connection);
    }

}
