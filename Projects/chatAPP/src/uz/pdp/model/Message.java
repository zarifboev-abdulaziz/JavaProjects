package uz.pdp.model;

public class Message {
    int id;
    String body;
    int senderId;
    String senderName;
    String receiverUserName;

    public Message() {
    }

    public Message(int id, String body, int senderId, String senderName, String receiverUserName) {
        this.id = id;
        this.body = body;
        this.senderId = senderId;
        this.senderName = senderName;
        this.receiverUserName = receiverUserName;
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

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
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
                ", senderId=" + senderId +
                ", senderName='" + senderName + '\'' +
                ", receiverUserName='" + receiverUserName + '\'' +
                '}';
    }
}
