package uz.pdp.model;

import uz.pdp.model.enums.Status;

public class CustomMessage {
    int id = (int) (Math.random()*10000);
    String body;
    Status status = Status.UNREAD;

    public CustomMessage(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
