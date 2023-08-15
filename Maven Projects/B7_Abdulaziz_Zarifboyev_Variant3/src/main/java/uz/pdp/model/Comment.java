
package uz.pdp.model;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Comment {

    @Expose
    private String body;
    @Expose
    private String email;
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private Long postId;

    public String getBody() {
        return body;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPostId() {
        return postId;
    }

    public static class Builder {

        private String body;
        private String email;
        private Long id;
        private String name;
        private Long postId;

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPostId(Long postId) {
            this.postId = postId;
            return this;
        }

        public Comment build() {
            Comment comment = new Comment();
            comment.body = body;
            comment.email = email;
            comment.id = id;
            comment.name = name;
            comment.postId = postId;
            return comment;
        }

    }

}
