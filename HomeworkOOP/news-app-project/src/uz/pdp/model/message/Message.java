package uz.pdp.model.message;

import uz.pdp.model.User;
import uz.pdp.model.enums.MessageStatus;

import static uz.pdp.model.enums.MessageStatus.UNREAD;

public class Message {

    private Integer id;
    private MessageStatus status = UNREAD;
    private String title;
    private String body;
    private User sender;
    private User reciever;

    public Message() {
    }

    public Message(Integer id, MessageStatus status, String title, String body, User sender, User reciever) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.body = body;
        this.sender = sender;
        this.reciever = reciever;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciever() {
        return reciever;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", sender=" + sender +
                ", reciever=" + reciever +
                '}';
    }
}
