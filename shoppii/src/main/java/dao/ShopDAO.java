package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Filters;
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

}