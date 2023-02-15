package forum.dao;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;


public class PostImgDao {

    private String URL = DaoId.URL;
    private String USER = DaoId.USER;
    private String PASSWORD = DaoId.PASSWORD;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//  public void insetImg(){
//      String sql = "insert into post_imgs (post_id, img_path) values(?,?);";
////      InputStream in = Files.newInputStream(Path.of("image.jpg")))
//      try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//           InputStream in =
//           PreparedStatement ps = connection.prepareStatement(sql)) {
//          ps.setInt(1, postId);
//          ps.setInt(2, userId);
//          ps.execute();
//      } catch (SQLException e) {
//      }
//  }
}


