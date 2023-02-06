package forum.dao;

import forum.pojo.Post;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class PostLikeDao {

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


    public void like_generator() {
        String sql = "insert into post_like(post_id, user_id) values (?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < 20; i++) {
                ps.setInt(1, (int) (Math.random() * 19) + 1);
                ps.setInt(2, (int) (Math.random() * 5) + 1);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
        }
    }

    public void like_clean() {
        String sql = "delete from post_like where 1=1;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void like_plus(int post_id, int user_id) {
        String sql = "insert into post_like(post_id, user_id) values (?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, post_id);
            ps.setInt(2, user_id);
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void like_minus(int post_id, int user_id) {
        String sql = "delete from post_like where post_id = ? and user_id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, post_id);
            ps.setInt(2, user_id);
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public boolean like_check(int post_id, int user_id) {
        String sql = "select * from post_like where post_id = ? and user_id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, post_id);
            ps.setInt(2, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int like_count(int post_id) {
//        int like = -1;
        String sql = "select post_id, count(*) as plike from post_like\n" +
                "where post_id = ?\n" +
                "group by post_id;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, post_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
              int  like = rs.getInt("plike");
                return like;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -5;
    }
}


