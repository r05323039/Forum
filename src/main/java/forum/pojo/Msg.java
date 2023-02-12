package forum.pojo;

import java.util.List;

public class Msg {
    private int msgId;
    private int postId;
    private int userId;
    private String userName;
    private String content;
    private PostImage preview;
    private int like;
    String timestamp;


    public Msg() {
    }

    public Msg(int msgId, int postId,int userId, String userName, String content, int like, String timestamp) {
        this.msgId = msgId;
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.like = like;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msgId=" + msgId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", preview=" + preview +
                ", like=" + like +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostImage getPreview() {
        return preview;
    }

    public void setPreview(PostImage preview) {
        this.preview = preview;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}




