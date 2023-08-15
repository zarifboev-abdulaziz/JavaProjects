package uz.pdp.model;

public class Message {
    int id;
    String body;
    String senderUserName;
    String senderName;
    String receiverUserName;

    public Message() {
    }

    public Message(int id, String body, String senderUserName, String senderName, String receiverUserName) {
        this.id = id;
        this.body = body;
        this.senderUserName = senderUserName;
        this.senderName = senderName;
        this.receiverUserName = receiverUserName;
    }

    public Message(int id, String body, String senderUserName, String senderName) {
        this.id = id;
        this.body = body;
        this.senderUserName = senderUserName;
        this.senderName = senderName;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverUserName() {
        return receiverUserName;
    }

    public void setReceiverUserName(String receiverUserName) {
        this.receiverUserName = receiverUserName;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", senderUserName='" + senderUserName + '\'' +
                ", senderName='" + senderName + '\'' +
                ", receiverUserName='" + receiverUserName + '\'' +
                '}';
    }
}
