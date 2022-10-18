package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Category;

public class CategoryDAO {
    public static Category getCategoryFromId(int category_id, Connection connection) throws SQLException {
        Category cate = null;
        String sql = "SELECT category_name FROM `Category` WHERE category_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, category_id);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String category_name = result.getString(1);
           cate = new Category(category_id, category_name);
          
        }
        return cate;
    }

    public static ArrayList<Category> getCategory(Connection connection) throws SQLException {
        ArrayList<Category> cate = new ArrayList<>();

        String sql = "SELECT * FROM `Category` ORDER BY category_id ASC";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int category_id = result.getInt(1);
            String category_name = result.getString(2);
            cate.add(new Category(category_id, category_name));
        }
        return cate;
    }

}