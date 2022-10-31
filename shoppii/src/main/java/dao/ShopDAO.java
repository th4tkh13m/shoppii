package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.Filters;
import model.Product;
import model.Shop;

public class ShopDAO {

    public static Shop getShopFromId(int shopId, Connection connection) throws SQLException {
        Shop shop = null;
        String sql = "SELECT name, address, description, status FROM `Shop` WHERE shop_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, shopId);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String name = result.getString(1);
            String address = result.getString(2);
            String description = result.getString(3);
            boolean status = result.getBoolean(4);

            shop = new Shop(shopId, name, address, description, status);
        }
        return shop;
    }

    public static Shop updateInformation(int shopId, String name, String address, String description,
            Connection connection) throws SQLException {
        String sql = "UPDATE `Shop` SET name = ?, address= ?, description= ?  WHERE shop_id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(4, shopId);
        statement.setString(1, name);
        statement.setString(2, address);
        statement.setString(3, description);
        statement.executeUpdate();
        return getShopFromId(shopId, connection);
    }

    public static String deleteShop(int shopId, Connection connection) throws SQLException {
        String sql = " UPDATE `Shop` SET status = 0 where shop_id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, shopId);
        statement.executeUpdate();
        return "Shop delete success!";
    }

    public static ArrayList<Shop> getLocationsShop(Connection connection) throws SQLException {
        ArrayList<Shop> locations = new ArrayList<>();
        String sql = "select shop_id, address from shop group by address";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int id = result.getInt(1);
            String address = result.getString(2);
            locations.add(new Shop(id, address));
        }
        return locations;
    }

    public static HashMap<Integer, ArrayList<Shop>> getShops(Filters filters, Connection connection)
            throws SQLException {
        HashMap<Integer, ArrayList<Shop>> shopsMap = new HashMap<>();
        ArrayList<Shop> products = new ArrayList<>();
        String sql = "SELECT * from shop";
        ArrayList<String> WHERE_CLAUSE_ARRAY = new ArrayList<>();
        String WHERE_CLAUSE = "WHERE";
        String LIMIT_CLAUSE = "";

        if (filters.getKeyword() != null) {
            WHERE_CLAUSE_ARRAY.add("LOWER(name) like '%" + filters.getKeyword() + "%'");
        }

        if (filters.getLocations() != null) {
            // WHERE_CLAUSE_ARRAY.add("LOWER(s.address) like '%" + filters.location + "%'");
            String[] locations = filters.getLocations();
            String locationsString = "(";
            for (int i = 0; i < locations.length; i++) {
                locationsString += "LOWER(address) like '%" + locations[i] + "%'";
                if (i < locations.length - 1) {
                    locationsString += " OR ";
                } else {
                    locationsString += ")";
                }
            }
            WHERE_CLAUSE_ARRAY.add(locationsString);
            System.out.println(locationsString);
        }
        int limit = filters.getLimit();
        int page = filters.getPage();
        int offset = (limit * page) - limit;
        LIMIT_CLAUSE = " LIMIT " + limit + " OFFSET " + offset;
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
        String sqlCount = "SELECT COUNT(shop_id) FROM Shop " + WHERE_CLAUSE;
        sql += " " + WHERE_CLAUSE + LIMIT_CLAUSE;
        System.out.println(sqlCount);
        System.out.println(sql);
        statement = connection.prepareStatement(sqlCount);
        ResultSet resultCount = statement.executeQuery();
        int totalPage = 1;
        if (resultCount.next()) {
            int count = resultCount.getInt(1);
            totalPage = (int) Math.ceil((double) count / limit);
            shopsMap.put(totalPage, products);
        }
        statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int shopId = result.getInt(1);
            Shop p = getShopFromId(shopId, connection);
            shopsMap.get(totalPage).add(p);
        }
        return shopsMap;
    }

    public static ArrayList<Integer> getTotalIncomes7Days(int shopId, String filter, Connection connection) throws SQLException {
        Date now = new Date((new java.util.Date()).getTime());
        String filterSql = null;
        if (filter.equals("incomes")) {
            filterSql = "c.quantity * c.price";
        } else filterSql = "c.quantity";
        String sql = "SELECT IFNULL(SUM(" + filterSql + "), 0), DATE_SUB(?, INTERVAL 1 DAY) FROM `Contain` c INNER JOIN `Product` p ON c.product_id = p.product_id INNER JOIN `Order` o ON c.order_id = o.order_id WHERE p.shop_id = ? AND o.time BETWEEN DATE_SUB(?, INTERVAL 1 DAY) AND ?";
        ArrayList<Integer> incomes = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setDate(1, now);
            statement.setInt(2, shopId);
            statement.setDate(3, now);
            statement.setDate(4, now);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.println(result.getInt(1));
                incomes.add(result.getInt(1));
                now = result.getDate(2);
            }

        }

        return incomes;
    }

    public static int getTotalAllTime(int shopId, String filter, Connection connection) throws SQLException {
        String filterSql = null;
        if (filter.equals("incomes")) {
            filterSql = "c.quantity * c.price";
        } else filterSql = "c.quantity";
        String sql = "SELECT SUM(" + filterSql + ") FROM `Contain` c INNER JOIN `Product` p ON c.product_id = p.product_id INNER JOIN `Order` o ON c.order_id = o.order_id WHERE p.shop_id = ?";
        
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            return result.getInt(1);
        }
        return 0;
    }

    // public static Product getMostSaledProduct(int shopId, Date startDate, Date endDate, Connection connection) {
    //     String sql = "SELECT ";
    // }
}