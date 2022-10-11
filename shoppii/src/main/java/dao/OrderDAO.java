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
import model.Order;
import model.OrderItem;
import model.Product;
import model.Shop;

public class OrderDAO {
    public static Order addOrder(int product_id, int quantity, int price, int user_id, String payment_method,
            String status, int address_id, Connection connection) {
        try {
            String sql = "INSERT INTO `Contain` (product_id, quantity, price) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product_id);
            statement.setInt(2, quantity);
            statement.setInt(3, price);
            statement.execute();
            String sql1 = "INSERT INTO `Order` (order_id,user_id, payment_method,status,time, address_id) VALUES(?,?,?,?,?,?)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, selectMaxOrder(connection));
            statement1.setInt(2, user_id);
            statement1.setString(3, payment_method);
            statement1.setString(4, status);
            statement1.setTime(5, new Time(System.currentTimeMillis()));
            statement1.setInt(6, address_id);
            statement1.execute();

            return new Order(selectMaxOrder(connection), user_id, payment_method, status,
                    new Time(System.currentTimeMillis()), address_id);

        } catch (Exception e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    private static int selectMaxOrder(Connection connection) throws SQLException {
        String sql = "SELECT MAX(order_id) FROM `Order`";
        PreparedStatement statement2 = connection.prepareStatement(sql);
        ResultSet result = statement2.executeQuery(sql);
        int order_id = 0;
        while (result.next()) {
            order_id = result.getInt(1);
        }
        return order_id;
    }

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

    // public static ArrayList<Order> createOrders(int customerId, int addressId,
    // String paymentMethod, ArrayList<Product> products, Connection connection) {
    // Or
    // }
}
