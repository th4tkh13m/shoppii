package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}