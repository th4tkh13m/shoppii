package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import dbconnect.S3Util;
import model.Address;
import model.Order;
import model.OrderItem;
import model.Product;
import model.Shop;

public class OrderDAO {
    public static ArrayList<Order> addOrder(HashMap<Shop, HashMap<Product, Integer>> confirmedProducts, int userId,
            String paymentMethod, int addressId, Connection connection) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        // Tao order theo shop, moi shop 1 ma order
        for (Shop shop : confirmedProducts.keySet()) {
            String sql1 = "INSERT INTO `Order` (user_id, payment_method, address_id) VALUES(?,?,?)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, userId);
            statement1.setString(2, paymentMethod);
            statement1.setInt(3, addressId);
            statement1.execute();

            for (Product product : confirmedProducts.get(shop).keySet()) {
                String sql = "INSERT INTO `Contain` (order_id, product_id, quantity, price) VALUES (?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, selectMaxOrder(connection));
                statement.setInt(2, product.getProductId());
                statement.setInt(3, confirmedProducts.get(shop).get(product));
                statement.setInt(4, confirmedProducts.get(shop).get(product) * product.getPrice());
                statement.execute();
            }

            orders.add(getOrderFromId(selectMaxOrder(connection), connection));
        }
        return orders;
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

    public static ArrayList<OrderItem> getOrderItemsFromId(int orderId, Connection connection) throws SQLException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
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
    }

    public static Order getOrderFromId(int orderId, Connection connection) throws SQLException {
        Order order = null;
        String sql = "SELECT user_id, payment_method, status, time, address_id FROM `Order` WHERE order_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, orderId);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int customerId = result.getInt(1);
            String paymentMethod = result.getString(2);
            String status = result.getString(3);
            Timestamp time = result.getTimestamp(4);
            Address address = AddressDAO.getAddressFromId(result.getInt(5), connection);

            order = new Order(orderId, customerId, paymentMethod, status, time, address);
        }

        return order;
    }

    public static ArrayList<Order> getOrdersByShop(int shopId, String statusFilter, Connection connection)
            throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT DISTINCT o.order_id, user_id, payment_method, status, time, address_id " +
                "FROM `Order` o INNER JOIN `Contain` c ON o.order_id = c.order_id " +
                "INNER JOIN `Product` p ON p.product_id = c.product_id " +
                "WHERE p.shop_id = ? AND status = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, shopId);
        if (statusFilter == null) {
            statement.setString(2, "%");
        } else {
            statement.setString(2, statusFilter);
        }
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            int orderId = result.getInt(1);
            int customerId = result.getInt(2);
            String paymentMethod = result.getString(3);
            String status = result.getString(4);
            Timestamp time = result.getTimestamp(5);
            Address address = AddressDAO.getAddressFromId(result.getInt(6), connection);

            orders.add(new Order(orderId, customerId, paymentMethod, status, time, address));
        }

        return orders;
    }

    private static Order changeStatus(int orderId, String status, Connection connection) throws SQLException {
        String sql = "UPDATE `Order` SET status = ? WHERE order_id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, status);
        statement.setInt(2, orderId);

        statement.executeUpdate();
        return getOrderFromId(orderId, connection);

    }

    public static ArrayList<Order> getOrdersOfUser(int userId, String status, Connection connection)
            throws SQLException {
        
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet result;
        PreparedStatement statement;
        if (status == null) {
            String sql = "SELECT order_id FROM `Order` WHERE user_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            result = statement.executeQuery();
        } else {
            String sql = "SELECT order_id FROM `Order` WHERE user_id = ? AND LOWER(`status`) LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, status);
            result = statement.executeQuery();
        }

        while (result.next()) {
            orders.add(getOrderFromId(result.getInt(1), connection));
        }
        return orders;
    }

    public static Order acceptOrder(int orderId, Connection connection) throws SQLException {
        return changeStatus(orderId, "Accepted", connection);

    }

    public static Order rejectOrder(int orderId, Connection connection) throws SQLException {
        return changeStatus(orderId, "Rejected", connection);
    }

    public static HashMap<Order, HashMap<Product, Integer>> getOrderWithItemsofUser(int userId, String status,
            Connection connection) throws SQLException {
        ArrayList<Order> orders = getOrdersOfUser(userId, status, connection);
        HashMap<Order, HashMap<Product, Integer>> orderMap = new HashMap<>();

        for (Order order : orders) {
            HashMap<Product, Integer> items = new HashMap<>();
            String sql = "SELECT product_id, quantity, price FROM Contain WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getOrderId());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Product product = ProductDAO.getProductFromId(result.getInt(1), connection);
                ArrayList<String> images = S3Util.listPhotos("products/" + product.getProductId() + "/");
                System.out.println(images);
                ArrayList<String> imagesUrl = new ArrayList<>();
                if (images.size() > 0) {

                    imagesUrl.add(images.get(0));
                }
                product.setImages(imagesUrl);
                product.setPrice(result.getInt(3));
                items.put(product, result.getInt(2));
            }

            orderMap.put(order, items);
        }

        return orderMap;
    }

    public static HashMap<Order, HashMap<Product, Integer>> getOrderWithItemsofShop(int shopId, String status,
            Connection connection) throws SQLException {
        ArrayList<Order> orders = getOrdersByShop(shopId, status, connection);
        HashMap<Order, HashMap<Product, Integer>> orderMap = new HashMap<>();

        for (Order order : orders) {
            HashMap<Product, Integer> items = new HashMap<>();
            String sql = "SELECT product_id, quantity, price FROM Contain WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getOrderId());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Product product = ProductDAO.getProductFromId(result.getInt(1), connection);
                ArrayList<String> images = S3Util.listPhotos("products/" + product.getProductId() + "/");
                System.out.println(images);
                ArrayList<String> imagesUrl = new ArrayList<>();
                if (images.size() > 0) {

                    imagesUrl.add(images.get(0));
                }
                product.setImages(imagesUrl);
                product.setPrice(result.getInt(3));
                items.put(product, result.getInt(2));
            }

            orderMap.put(order, items);
        }

        return orderMap;
    }
}
