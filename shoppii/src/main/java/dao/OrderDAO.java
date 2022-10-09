package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.OrderItem;
import model.Product;
import model.Shop;

public class OrderDAO {
    public static ArrayList<OrderItem> getOrderItemsFromId(int orderId, Connection connection) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        try {
            String sql = "SELECT product_id, quantity, price FROM `Contain` WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, orderId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int productId = result.getInt(1);
                int quantity = result.getInt(2);
                int price = result.getInt(3);

                Product product = ProductDAO.getProductFromId(productId, connection);

                orderItems.add(new OrderItem(product, quantity, price));
            }
            return orderItems;
        } catch (Exception e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
            return orderItems;
        }
    }

    public static Order getOrderFromId(int orderId, Connection connection) {
        Order order = null;
        try {
            String sql = "SELECT user_id, payment_method, status, time, address_id FROM `Contain` WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, orderId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int customerId = result.getInt(1);
                String paymentMethod = result.getString(2);
                String status = result.getString(3);
                Time time = result.getTime(4);
                int addressId = result.getInt(5);

                
                order = new Order(orderId, customerId, paymentMethod, status, time, addressId);
            }

            return order;
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
            return order;
        }
    }

    public static ArrayList<Order> getOrdersByShop(Shop shop, String statusFilter, Connection connection) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT order_id, user_id, payment_method, status, time, address_id " +
            "FROM `Order` o INNER JOIN `Contain` c ON o.order_id = c.order_id " +
            "INNER JOIN `Product` p ON p.product_id = c.product_id " +
            "WHERE p.shop_id = ? AND status = ?";


            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, shop.getShopId());
            statement.setString(2, statusFilter);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int orderId = result.getInt(1);
                int customerId = result.getInt(2);
                String paymentMethod = result.getString(3);
                String status = result.getString(4);
                Time time = result.getTime(5);
                int addressId = result.getInt(6);

           
                orders.add(new Order(orderId, customerId, paymentMethod, status, time, addressId));
            }

            return orders;
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
            return orders;
        }
    }

    private static boolean changeStatus(int orderId, String status, Connection connection) {
        try {
            String sql = "UPDATE `Order` SET status = ? WHERE order_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, orderId);

            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public static boolean acceptOrder(int orderId, Connection connection) {
        return changeStatus(orderId, "Accepted", connection);
        
    }

    public static boolean rejectOrder(int orderId, Connection connection) {
        return changeStatus(orderId, "Rejected", connection);
    }

    
    // public static ArrayList<Order> createOrders(int customerId, int addressId, String paymentMethod, ArrayList<Product> products, Connection connection) {
    //     Or
    // }
}
