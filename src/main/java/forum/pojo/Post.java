package forum.pojo;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
    private int postId;
    private int userId;
    private String userName;
    private String category;
    private String topic;
    private String content;
    private List<PostImage> imgsInPost;
    private PostImage preview;
    int like;
    String timestamp;




    public Post(int postId, int userId, String userName, String category, String topic, String content, int like, String timestamp) {
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.category = category;
        this.topic = topic;
        this.content = content;
        this.like = like;
        this.timestamp = timestamp;
    }


    public Post() {

    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", category='" + category + '\'' +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", imgsInPost=" + imgsInPost +
                ", preview=" + preview +
                ", like=" + like +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<PostImage> getImgsInPost() {
        return imgsInPost;
    }

    public void setImgsInPost(List<PostImage> imgsInPost) {
        this.imgsInPost = imgsInPost;
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




