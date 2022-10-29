package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.Filters;
import model.Product;

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

            product = new Product(productId, name, price, quantity, description,
                    ShopDAO.getShopFromId(shopId, connection), CategoryDAO.getCategoryFromId(categoryId, connection));
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

    public static ArrayList<Product> getProductByShopId(int shopId, String keyword, Connection connection)
            throws SQLException {
        ArrayList<Product> list = new ArrayList<>();
        String sql;
        if (keyword == null) {
            sql = "SELECT product_id from Product WHERE shop_id = ?";
        } else {
            sql = "SELECT product_id from Product WHERE shop_id = ? AND name LIKE '%" + keyword + "%'";
        }

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
        statement.setInt(1, product.getShop().getShopId());
        statement.setString(2, product.getName());
        statement.setInt(3, product.getPrice());
        statement.setInt(4, product.getQuantity());
        statement.setInt(5, product.getCategory().getCategory_id());
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
        statement.setInt(4, product.getCategory().getCategory_id());
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

    public static HashMap<Integer, ArrayList<Product>> getProducts(Filters filters, Connection connection)
            throws SQLException {
        HashMap<Integer, ArrayList<Product>> productsMap = new HashMap<>();
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT product_id, category_id FROM Product pd inner join Shop s on pd.shop_id = s.shop_id";
        ArrayList<String> WHERE_CLAUSE_ARRAY = new ArrayList<>();
        String WHERE_CLAUSE = "WHERE";
        String ORDER_BY_CLAUSE = "";
        String LIMIT_CLAUSE = "";

        if (filters.getKeyword() != null) {
            WHERE_CLAUSE_ARRAY.add("LOWER(pd.name) like '%" + filters.getKeyword() + "%'");
        }
        if (filters.getCategoriesId() != null) {
            // WHERE_CLAUSE_ARRAY.add("pd.category_id = " + filters.get());
            String[] categoriesId = filters.getCategoriesId();
            String categoriesIdString = "(";
            for (int i = 0; i < categoriesId.length; i++) {
                categoriesIdString += "category_id = " + categoriesId[i];
                if (i < categoriesId.length - 1) {
                    categoriesIdString += " OR ";
                } else {
                    categoriesIdString += ")";
                }
            }
            WHERE_CLAUSE_ARRAY.add(categoriesIdString);
        }
        if (filters.getLocations() != null) {
            // WHERE_CLAUSE_ARRAY.add("LOWER(s.address) like '%" + filters.location + "%'");
            String[] locations = filters.getLocations();
            String locationsString = "(";
            for (int i = 0; i < locations.length; i++) {
                locationsString += "LOWER(s.address) like '%" + locations[i] + "%'";
                if (i < locations.length - 1) {
                    locationsString += " OR ";
                } else {
                    locationsString += ")";
                }
            }
            WHERE_CLAUSE_ARRAY.add(locationsString);
            System.out.println(locationsString);
        }
        if (filters.getStartPrice() != null) {
            WHERE_CLAUSE_ARRAY.add("pd.price >= " + filters.getStartPrice());
        }
        if (filters.getEndPrice() != null) {
            WHERE_CLAUSE_ARRAY.add("pd.price <= " + filters.getEndPrice());
        }
        int limit = filters.getLimit();
        int page = filters.getPage();
        int offset = (limit * page) - limit;
        LIMIT_CLAUSE = " LIMIT " + limit + " OFFSET " + offset;
        if (filters.getSort() != null) {
            ORDER_BY_CLAUSE = " ORDER BY pd.price " + filters.getSort();
        }
        if (WHERE_CLAUSE_ARRAY.size() < 1) {
            WHERE_CLAUSE = "";
        } else {
            for (String sentences : WHERE_CLAUSE_ARRAY) {
                if (WHERE_CLAUSE_ARRAY.indexOf(sentences) == 0) {
                    WHERE_CLAUSE += " " + sentences;
                } else {
                    WHERE_CLAUSE += " AND " + sentences;
                }
            }
        }
        System.out.println(WHERE_CLAUSE);
        // System.out.println(ORDER_BY_CLAUSE);
        // System.out.println(LIMIT_CLAUSE);
        PreparedStatement statement;
        String sqlCount = "SELECT COUNT(product_id) FROM Product pd inner join Shop s on pd.shop_id = s.shop_id" + " "
                + WHERE_CLAUSE;
        sql += " " + WHERE_CLAUSE + ORDER_BY_CLAUSE + LIMIT_CLAUSE;
        System.out.println(sqlCount);
        System.out.println(sql);
        statement = connection.prepareStatement(sqlCount);
        ResultSet resultCount = statement.executeQuery();
        int count = 0;
        if (resultCount.next()) {
            count = resultCount.getInt(1);
            productsMap.put(count, products);
        }
        statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int productId = result.getInt(1);
            Product p = getProductFromId(productId, connection);
            productsMap.get(count).add(p);
        }
        return productsMap;
    }

    // filter

    // sort
}
