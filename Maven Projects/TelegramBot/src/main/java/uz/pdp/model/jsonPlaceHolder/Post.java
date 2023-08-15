
package uz.pdp.model.jsonPlaceHolder;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Post {

    @Expose
    private String body;
    @Expose
    private Long id;
    @Expose
    private String title;
    @Expose
    private Long userId;

    public String getBody() {
        return body;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getUserId() {
        return userId;
    }

    public static class Builder {

        private String body;
        private Long id;
        private String title;
        private Long userId;

        public Post.Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public Post.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Post.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Post.Builder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Post build() {
            Post post = new Post();
            post.body = body;
            post.id = id;
            post.title = title;
            post.userId = userId;
            return post;
        }

    }

}
