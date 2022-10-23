package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import model.Shop;
import model.ShopRequest;

public class ShopDAO {

    public static Shop updateInformation(int shopId, String name, String address, String description,
            Connection connection) throws SQLException {
        String sql = "UPDATE `Shop` SET name = ?, address= ?, description= ?  WHERE shop_id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(4, shopId);
        statement.setString(1, name);
        statement.setString(2, address);
        statement.setString(3, description);
        statement.executeUpdate();
        Shop shop = new Shop(name, address, description);
        return shop;
    }
}
