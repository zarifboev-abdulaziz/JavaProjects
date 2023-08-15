package uz.pdp.model;

import java.util.List;

public class Group {
    int id;
    String name;
    User groupAdmin;
    List<User> members;
    List<Message> groupMessageList;


    public Group() {
    }

    public Group(int id, String name, User groupAdmin, List<User> members, List<Message> groupMessageList) {
        this.id = id;
        this.name = name;
        this.groupAdmin = groupAdmin;
        this.members = members;
        this.groupMessageList = groupMessageList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(User groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Message> getGroupMessageList() {
        return groupMessageList;
    }

    public void setGroupMessageList(List<Message> groupMessageList) {
        this.groupMessageList = groupMessageList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupAdmin=" + groupAdmin +
                ", members=" + members +
                ", groupMessageList=" + groupMessageList +
                '}';
    }
}
