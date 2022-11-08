package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import dbconnect.S3Util;
import model.Order;
import model.Product;
import model.Shop;

public class CartDAO {
    private static int getQuantityOfProduct(int customerId, int productId, Connection connection) throws SQLException {
        String shopSql = "SELECT quantity FROM Cart WHERE user_id = ? AND product_id = ?";
        PreparedStatement statement = connection.prepareStatement(shopSql);
        statement.setInt(1, customerId);
        statement.setInt(2, productId);
        int quantity = 0;

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            quantity = result.getInt(1);
        }
        return quantity;
    }

    public static HashMap<Shop, HashMap<Product, Integer>> getCartOfCustomer(int customerId, Connection connection)
            throws SQLException {
        HashMap<Shop, HashMap<Product, Integer>> cart = new HashMap<>();

        // Get Shop List
        String shopSql = "SELECT DISTINCT shop_id FROM Cart INNER JOIN Product ON Cart.product_id = Product.product_id WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(shopSql);
        statement.setInt(1, customerId);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            cart.put(ShopDAO.getShopFromId(result.getInt(1), connection), new HashMap<>());
        }

        // Put shop's product into hashmap
        for (Shop shop : cart.keySet()) {
            String sql = "SELECT Cart.product_id, Cart.quantity FROM Cart INNER JOIN Product ON Cart.product_id = Product.product_id WHERE user_id = ? AND shop_id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setInt(2, shop.getShopId());

            result = statement.executeQuery();
            while (result.next()) {
                Product product = ProductDAO.getProductFromId(result.getInt(1), connection);
                ArrayList<String> images =
                S3Util.listPhotos("products/" + product.getProductId() + "/");
                System.out.println(images);
                ArrayList<String> imagesUrl = new ArrayList<>();
            if (images.size() > 0) {
                
                imagesUrl.add(images.get(0));
            }
            product.setImages(imagesUrl);
                int quantity = result.getInt(2);
                cart.get(shop).put(product, quantity);
                cart.put(shop, cart.get(shop));
            }
        }
        return cart;
    }

    public static HashMap<Shop, HashMap<Product, Integer>> addProductToCart(int customerId, int productId, int quantity,
            Connection connection) throws SQLException {
        int oldQuantity = getQuantityOfProduct(customerId, productId, connection);

        if (oldQuantity != 0) {
            int newQuantity = oldQuantity + quantity;
            String sql = "UPDATE Cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, newQuantity);
            statement.setInt(2, customerId);
            statement.setInt(3, productId);
            statement.executeUpdate();
        } else {
            String sql = "INSERT INTO Cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            statement.executeUpdate();
        }
        return getCartOfCustomer(customerId, connection);
    }

    public static HashMap<Shop, HashMap<Product, Integer>> deleteProductFromCart(int customerId, int productId,
            Connection connection) throws SQLException {
        String sql = "DELETE FROM Cart WHERE user_id = ? AND product_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, customerId);
        statement.setInt(2, productId);
        statement.executeUpdate();

        return getCartOfCustomer(customerId, connection);
    }

    public static HashMap<Shop, HashMap<Product, Integer>> modifyProductQuantity(int customerId, int productId,
            int quantity, Connection connection) throws SQLException {
        // int newQuantity = getQuantityOfProduct(customerId, productId, connection) +
        // quantity;

        if (quantity > 0) {
            String sql = "UPDATE Cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, customerId);
            statement.setInt(3, productId);
            statement.executeUpdate();
        } else {
            deleteProductFromCart(customerId, productId, connection);
        }
        return getCartOfCustomer(customerId, connection);
    }

    public static boolean removeAfterCheckout(ArrayList<Order> orders, Connection connection) throws SQLException {
        String sql = "DELETE FROM `Cart` WHERE (product_id, user_id) IN (SELECT product_id, user_id FROM `Order` o INNER JOIN `Contain` c ON o.order_id = c.order_id WHERE c.order_id = ?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        for (Order order : orders) {
            statement.setInt(1, order.getOrderId());
            statement.executeUpdate();
        }

        return true;
    }

    public static boolean removeAfterDeleteProduct(Connection connection) throws SQLException {
        String sql = "DELETE FROM `Cart` WHERE product_id IN (SELECT product_id FROM `Product` WHERE is_available IS FALSE)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        return true;
    }
}
