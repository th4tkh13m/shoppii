package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.Product;
import model.Shop;

public class CartDAO {
    public static HashMap<Shop, HashMap<Product, Integer>> getCartOfCustomer(int customerId, Connection connection) throws SQLException {
        HashMap<Shop, HashMap<Product, Integer>> cart = new HashMap<>();

        // Get Shop List
        String shopSql = "SELECT DISTINCT shop_id FROM Cart INNER JOIN Product ON Cart.product_id = Product.product_id WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(shopSql);
        statement.setInt(1, customerId);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            cart.put(CustomerDAO.getShopFromId(result.getInt(1), connection), new HashMap<>());
        }

        // Put shop's product into hashmap
        for (Shop shop : cart.keySet()) {
            String sql = "SELECT Cart.product_id, quantity FROM Cart INNER JOIN Product ON Cart.product_id = Product.product_id WHERE user_id = ? AND shop_id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);

            result = statement.executeQuery();
            while (result.next()) {
                Product product = ProductDAO.getProductFromId(result.getInt(2), connection);
                int quantity = result.getInt(3);
                cart.get(shop).put(product, quantity);
                cart.put(shop, cart.get(shop));
            }
        }
        return cart;
    }
}
