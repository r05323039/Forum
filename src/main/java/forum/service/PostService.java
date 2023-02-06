package forum.service;

import forum.dao.PostDao;
import forum.dao.PostLikeDao;
import forum.pojo.Post;

import java.util.ArrayList;


public class PostService {
    private PostDao postDao = new PostDao();
    private PostLikeDao postLikeDao = new PostLikeDao();

    public ArrayList<Post> getAll(boolean order, String category_id, String topic) {
        ArrayList<Post> posts = new ArrayList<>();
        if (order) {
            posts = postDao.selectPopular();
        } else {
            posts = postDao.selectAll();
        }

        if (category_id != null) {
            String category = postDao.getCategoryById(Integer.parseInt(category_id));
            posts = categoryFilter(posts, category);
        }
        if (topic != null) {
            posts = topicFilter(posts, topic);
        }
        return posts;
    }
    public ArrayList<Post> categoryFilter(ArrayList<Post> posts, String category) {
        ArrayList<Post> filtered_posts = new ArrayList<>();
        for (Post p : posts) {
            if (category.equals(p.getCategory()))
                filtered_posts.add(p);
        }
        return filtered_posts;
    }
    public ArrayList<Post> topicFilter(ArrayList<Post> posts, String topic) {
        ArrayList<Post> filtered_posts = new ArrayList<>();
        for (Post p : posts) {
            if (p.getTopic().contains(topic))
                filtered_posts.add(p);
        }
        return filtered_posts;
    }
    public void like_generator() {
        postLikeDao.like_generator();
    }
    public void like_clean() {
        postLikeDao.like_clean();
    }
    public void like_click(int post_id, int user_id) {
        if (postLikeDao.like_check(post_id, user_id)) {
            postLikeDao.like_minus(post_id, user_id);
        } else {
            postLikeDao.like_plus(post_id, user_id);
        }

    }
}





