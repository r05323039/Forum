package forum.dao;

import forum.controller.ForumServlet;
import forum.pojo.Category;
import forum.pojo.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private String URL = "jdbc:mysql://localhost:3306/elitebaby";
    private String USER = "ian";
    private String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Category> selectAll() {
        String sql = "select * from category;";
        ArrayList<Category> categories = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(
                        new Category(
                                rs.getInt("id"),
                                rs.getString("category"),
                                rs.getString("img"),
                                rs.getInt("level")
                        ));
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}