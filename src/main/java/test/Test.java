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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Test {


    public static void main(String[] args) throws IOException {
        ArrayList<String> categoryNames = new ArrayList<>(Arrays.asList("寵物", "健康"));
        System.out.println(categoryNames);
        categoryNames.add("書");        categoryNames.add("書");
        StringBuilder sqlBuilder = new StringBuilder("select p.*, ac.user_name, count(l.like_id) as plike\n" +
                "from post p\n" +
                "         left join post_like l on p.post_id = l.post_id\n" +
                "         join access ac on ac.user_id = p.user_id\n" +
                "where p.category in (?");
        for (int i = 0; i < categoryNames.size(); i++) {
            if (i > 0){
                sqlBuilder.append(" ,?");
            }
        }
        sqlBuilder.append(") group by l.post_id;");
        String sql = sqlBuilder.toString();
        System.out.println(sql);
////                "group by l.post_id;";);
//        for (int i = 0; i < ids.size(); i++) {
//            if (i > 0) {
//                sqlBuilder.append(",?");
//            }
//        }
//        sqlBuilder.append(")");
//        String sql = sqlBuilder.toString();

    }
}