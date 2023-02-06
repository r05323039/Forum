package test;

import forum.pojo.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    private String URL = "jdbc:mysql://localhost:3306/elitebaby";
    private String USER = "ian";
    private String PASSWORD = "yi711044";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}


