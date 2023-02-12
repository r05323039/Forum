package forum.pojo;

import java.util.ArrayList;

public class PostBean {
    private Post post;
    private ArrayList<Msg> msgs;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public ArrayList<Msg> getMsgs() {
        return msgs;
    }

    public void setMsgs(ArrayList<Msg> msgs) {
        this.msgs = msgs;
    }
}
