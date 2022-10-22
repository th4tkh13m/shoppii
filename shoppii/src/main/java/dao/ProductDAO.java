package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import model.Product;
import model.Category;
import model.Shop;

public class ProductDAO {
    public static int getMaxId(Connection connection) throws SQLException {
        String sql = "SELECT MAX(product_id) FROM `Product`";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            return result.getInt(1);
        }
        return 0;
    }

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
        while (result.next()) {
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
        return getProductFromId(getMaxId(connection), connection);
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
        return getProductFromId(product.getProductId(), connection);
    }

    public static Product deleteProduct(int productId, Connection connection) throws SQLException {
        String sql = "DELETE FROM Product WHERE product_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productId);
        statement.executeUpdate();
        return getProductFromId(productId, connection);
    }

    // search
    public static ArrayList<Product> searchProduct(String keyword, Connection connection) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = " SELECT product_id FROM `Product` WHERE LOWER(name) like ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + keyword + "%");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int productId = result.getInt(1);
            Product p = getProductFromId(productId, connection);
            products.add(p);
        }
        return products;
    }

    public static ArrayList<Product> getProducts(Filters filters, Connection connection) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT pd.product_id, pd.shop_id, pd.name, pd.price, pd.quantity, pd.description,  pd.category_id, ct.category_name, ct.imgLink, pd.shop_id, s.name, s.address from (product pd inner join category ct on pd.category_id = ct.category_id) inner join shop s on s.shop_id = pd.shop_id";
        ArrayList<String> WHERE_CLAUSE_ARRAY = new ArrayList<>();
        String WHERE_CLAUSE = "WHERE";
        String ORDER_BY_CLAUSE = "";
        String LIMIT_CLAUSE = "";
        // WHERE_CLAUSE
        System.out.println("keyword" + filters.getKeyword());
        System.out.println("cate id" + filters.getCategoryId());
        System.out.println("location" + filters.getLocation());
        System.out.println("start price" + filters.getStartPrice());
        System.out.println("end price" + filters.getEndPrice());
        System.out.println("limit" + filters.getLimit());
        System.out.println("page" + filters.getPage());

        if (filters.getKeyword() != null) {
            WHERE_CLAUSE_ARRAY.add("LOWER(pd.name) like '%" + filters.getKeyword() + "%'");
        }
        if (filters.getCategoryId() != null) {
            WHERE_CLAUSE_ARRAY.add("pd.category_id = " + filters.getCategoryId());
        }
        if (filters.getLocation() != null) {
            WHERE_CLAUSE_ARRAY.add("LOWER(s.address) like '%" + filters.location + "%'");
        }
        if (filters.getStartPrice() != null) {
            WHERE_CLAUSE_ARRAY.add("pd.price >= " + filters.getStartPrice());
        }
        if (filters.getEndPrice() != null) {
            WHERE_CLAUSE_ARRAY.add("pd.price <= " + filters.getEndPrice());
        }
        if (WHERE_CLAUSE.equalsIgnoreCase("WHERE")) {
            WHERE_CLAUSE = "";
        }
        int limit = filters.getLimit();
        int page = filters.getPage();
        int offset = (limit * page) - limit;
        LIMIT_CLAUSE = " LIMIT " + limit + " OFFSET " + offset;
        if (filters.getSort() != null) {
            ORDER_BY_CLAUSE = " ORDER BY pd.price " + filters.getSort();
        }
        for (String sentences : WHERE_CLAUSE_ARRAY) {
            if (WHERE_CLAUSE_ARRAY.indexOf(sentences) == 0) {
                WHERE_CLAUSE += " " + sentences;
            } else {
                WHERE_CLAUSE += " AND " + sentences;
            }

        }
        System.out.println(WHERE_CLAUSE);
        System.out.println(ORDER_BY_CLAUSE);
        System.out.println(LIMIT_CLAUSE);
        sql += " " + WHERE_CLAUSE + ORDER_BY_CLAUSE + LIMIT_CLAUSE;
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int productId = result.getInt(1);
            int shopId = result.getInt(2);
            String name = result.getString(3);
            int price = result.getInt(4);
            int quantity = result.getInt(5);
            String description = result.getString(6);
            int categoryId = result.getInt(7);
            String categoryName = result.getString(8);
            String categoryImgLink = result.getString(9);
            String shopName = result.getString(11);
            String shopAddress = result.getString(12);
            Shop shop = new Shop(shopId, shopName, shopAddress);
            Category category = new Category(categoryId, categoryName, categoryImgLink);
            Product p = new Product(productId, name, price, quantity, description, shop, category);
            // Product p = getProductFromId(productId, connection);
            products.add(p);
        }
        return products;
    }

    // filter

    // sort
}
