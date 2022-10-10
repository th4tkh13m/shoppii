package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Product;

public class ProductDAO {
    public static Product getProductFromId(int productId, Connection connection) throws SQLException {
        Product product = null;
        String sql = "SELECT shop_id, name, price, quantity, category_id, description FROM `Product` WHERE product_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, productId);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int shopId = result.getInt(1);
            String name = result.getString(2);
            int price = result.getInt(3);
            int quantity = result.getInt(4);
            int categoryId = result.getInt(5);
            String description = result.getString(6);

            product = new Product(productId, shopId, name, price, quantity, categoryId, description);
        }

        return product;
    }

    public static ArrayList<Product> getAllProduct(Connection connection) throws SQLException {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT product_id from Product";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int id = result.getInt(1);
            Product p = getProductFromId(id, connection);
            list.add(p);
        }
        return list;
    }

    public static ArrayList<Product> getProductByShopId(int shopId, Connection connection) throws SQLException {
        ArrayList<Product> list = new ArrayList<>();
            String sql = "SELECT product_id FROM Product where shop_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, shopId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int id = result.getInt(1);
                Product p = getProductFromId(id, connection);
                list.add(p);
            }
            return list;
    }

    public static Product addProduct(Product product, Connection connection) throws SQLException {
        String sql = "INSERT INTO Product (shop_id, name, price, quantity, category_id, description) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, product.getShopId());
        statement.setString(2, product.getName());
        statement.setInt(3, product.getPrice());
        statement.setInt(4, product.getQuantity());
        statement.setInt(5, product.getcategoryId());
        statement.setString(6, product.getDescription());
        statement.executeUpdate();
        return product;
    }

    public static Product updateProduct(Product product, Connection connection) throws SQLException {
        String sql = "UPDATE Product SET name = ?, price = ?, quantity = ?, category_id = ?, description = ? WHERE product_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, product.getName());
        statement.setInt(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setInt(4, product.getcategoryId());
        statement.setString(5, product.getDescription());
        statement.setInt(6, product.getProductId());
        statement.executeUpdate();
        return product;
    }

    public static Product deleteProduct(int productId, Connection connection) throws SQLException{
        String sql = "DELETE FROM Product WHERE product_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productId);
        statement.executeUpdate();
        return getProductFromId(productId, connection);
    }
}
