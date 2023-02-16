package test;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class Test {
    public final static String URL = "jdbc:mysql://localhost:3306/elitebaby";
    public final static String USER = "root";
    public final static String PASSWORD = "password";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<InputStream> ins = new ArrayList<>();
        ins.add(new FileInputStream("D:/aaa/33.jpg"));
        ins.add(new FileInputStream("D:/aaa/44.jpg"));
        for (InputStream in : ins)
            System.out.println(in);
        int postId = 40;

        File directory = new File("src/main/webapp/images");
        if (!directory.exists()) {
            directory.mkdir();
        }
        String dirPath = directory.getPath();

        String sql = "insert into post_imgs (post_id, img_path) values(?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < ins.size(); i++) {
                ps.setInt(1, postId);
                String fileName = dirPath + "/" + postId + "_" + i;
                Files.copy(ins.get(i), Paths.get(fileName + ".jpg"),
                        StandardCopyOption.REPLACE_EXISTING);
                ps.setString(2, fileName);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
