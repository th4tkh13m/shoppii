package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Product;

public class ProductDAO {
    public static Product getProductFromId(int productId, Connection connection) {
        Product product = null;
        try {
            String sql = "SELECT shop_id, name, price, quantity, category, description FROM `Product` WHERE product_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, productId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int shopId = result.getInt(1);
                String name = result.getString(2);
                int quantity = result.getInt(3);
                int price = result.getInt(4);
                String category = result.getString(5);
                String description = result.getString(6);

                product = new Product(productId, shopId, name, price, quantity, category, description);
            }

            return product;
        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
            return product;
        }
    }

}
