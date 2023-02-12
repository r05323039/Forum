package forum.dao;
import forum.pojo.Msg;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
public class MsgDao {

    private String URL = DaoId.URL;
    private String USER = DaoId.USER;
    private String PASSWORD = DaoId.PASSWORD;
    private SimpleDateFormat format = new SimpleDateFormat("M月d日 HH:mm");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Msg> selectMsgsIdAsc(int postId) {
        String sql = "select msg.*, m.user_name, count(ml.like_id) as mLike\n" +
                "from msg join member m on m.USER_ID = msg.user_id\n" +
                "    join msg_like ml on msg.msg_id = ml.msg_id\n" +
                "where post_id = ?\n" +
                "group by msg.msg_id\n" +
                "order by msg_id asc;";
        ArrayList<Msg> msgs = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,postId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                msgs.add(
                        new Msg(
                                rs.getInt("msg_id"),
                                rs.getInt("post_id"),
                                rs.getInt("user_id"),
                                rs.getString("user_name"),
                                rs.getString("content"),
                                rs.getInt("mlike"),
                                format.format(rs.getObject("timing", Timestamp.class))
                        ));
            }
            return msgs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


