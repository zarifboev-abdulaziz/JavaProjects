package uz.pdp.model;

import uz.pdp.Main;
import uz.pdp.model.enums.Status;

public class News {
    private int id;
    private String title;
    private String body;
    private User author;
    private Status status;


    public News() {
    }

    public News(int id, String title, String body, User author, Status status) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", author=" + author +
                ", status=" + status +
                '}';
    }
}
