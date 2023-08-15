package uz.pdp.service;

import uz.pdp.Store;
import uz.pdp.model.Group;
import uz.pdp.model.Message;
import uz.pdp.model.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GroupServiceImpl implements GroupService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    @Override
    public void myGroups(User user) {
        int countMeIsAdmin = 0;
        Group selectedGroup = null;

        for (Group group : Store.groupList) {
            if(group.getGroupAdmin().getUserName().equals(user.getUserName())){
                countMeIsAdmin++;
                System.out.println("Id: " + group.getId() + ", Name: " + group.getName());
            }
        }

        if (countMeIsAdmin == 0){
            System.out.println("You have no groups yet!!!!!");
            return;
        }

        System.out.println("Enter group id: ");
        int groupId = scannerInt.nextInt();

        boolean isFound = false;
        for (Group group : Store.groupList) {
            if(group.getId() == groupId){
                selectedGroup = group;
                isFound = true;
                break;
            }
        }

        if (isFound){
            groupAdminMenu(selectedGroup, user);
        }else {
            System.out.println("Wrong id!!!");
        }


    }

    private void groupAdminMenu(Group group, User user) {
        while (true){
            System.out.println("1.Show Group Chat\n2.Add member\n3.Delete Member\n4.show members\n5.delete Group\n6.back");
           int option = scannerInt.nextInt();
           switch (option){
               case 1:
                   showGroupChat(group, user);
                   break;
               case 2:
                   addMember(group, user);
                   break;
               case 3:
                   deleteMember(group);
                   break;
               case 4:
                   showMembers(group);
                   break;
               case 5:
                   deleteGroup(group);
                   break;
               case 6: return;
               default:
                   System.out.println("Wrong option");
                   break;
           }
        }
    }

    private void deleteGroup(Group group) {
        Store.groupList.remove(group);
        System.out.println("Successfully removed!!!");
    }

    private void deleteMember(Group group) {
        if(group.getMembers().size() == 0){
            System.out.println("No members to delete!!!");
            return;
        }

        showMembers(group);
        System.out.println("Enter username to delete: ");
        String userName = scannerStr.nextLine();

        boolean isFound = false;
        for (User user : Store.userList) {
            if(user.getUserName().equals(userName)){
                group.getMembers().remove(user);
                System.out.println("Successfully deleted!!!");
                isFound = true;
                break;
            }
        }
        if(!isFound){
            System.out.println("User not found!!!");
        }
    }

    @Override
    public void groups(User user) {
        while (true){
            System.out.println("1.show My groups\n2.back");
            int option = scannerInt.nextInt();
            switch (option){
                case 1:
                    showMyGroups(user);
                    break;
                case 2: return;
                default:
                    System.out.println("Wrong option");
            }
        }

    }

    private void showMyGroups(User user) {
        int countGroup = 0;
        Group selectedGroup = null;

        if(Store.groupList.size() == 0){
            System.out.println("no groups!!!");
            return;
        }


        for (Group group : Store.groupList) {
            if (group.getMembers().contains(user)){
                countGroup++;
                System.out.println("id: " + group.getId() + ", Group name: " + group.getName());
            }
        }

        if(countGroup == 0){
            System.out.println("No available groups!!!");
            return;
        }

        System.out.println("Enter group id to select: ");
        int groupId = scannerInt.nextInt();

        boolean isFound = false;
        for (Group group : Store.groupList) {
            if(group.getId() == groupId){
                isFound = true;
                selectedGroup = group;
                break;
            }
        }

        if(!isFound){
            System.out.println("Group not found!!!");
            return;
        }

        selectedGroupMenu(selectedGroup, user);


    }

    @Override
    public void createGroup(User user) {

        int id = (int)(Math.random()*10000);

        System.out.println("Enter name of the group: ");
        String name = scannerStr.nextLine();

        List<User> groupMembers = new ArrayList<>();
        List<Message> groupMessageList = new ArrayList<>();

        Group group = new Group(id, name, user, groupMembers, groupMessageList);
        Store.groupList.add(group);
        System.out.println("Successfully created!!!");

    }

    @Override
    public void selectedGroupMenu(Group group, User user) {
        while (true){
            System.out.println("1.Show group chat\n2.add member to this group\n3.show members\n4.show admin\n5.Leave group\n6.back");
            int option = scannerInt.nextInt();
            switch (option){
                case 1:
                    showGroupChat(group, user);
                    break;
                case 2:
                    addMember(group, user);
                    break;
                case 3:
                    showMembers(group);
                    break;
                case 4:
                    showAdmin(group);
                    break;
                case 5:
                    leaveGroup(group, user);
                    return;
                case 6: return;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }

    private void leaveGroup(Group group, User user) {
        for (User member : group.getMembers()) {
            if(member.getUserName().equals(user.getUserName())){
                group.getMembers().remove(user);
                System.out.println("Successfully left");
                return;
            }
        }
    }


    private void showAdmin(Group group) {
        System.out.println("Admin name: " + group.getGroupAdmin().getFullName() + ", userName: " + group.getGroupAdmin().getUserName());
    }

    private void showMembers(Group group) {
        if (group.getMembers().size() == 0){
            System.out.println("List is empty!!!");
            return;
        }
        for (User member : group.getMembers()) {
            System.out.println("Name: " + member.getFullName() + ", userName: " + member.getUserName());
        }
    }

    private void addMember(Group group, User user) {
        System.out.println("Enter userName of person to add: ");
        String userName = scannerStr.nextLine();

        boolean isFound = false;
        for (User user1 : Store.userList) {
            if(user1.getUserName().equals(userName)){
                if (group.getMembers().contains(user1)) {
                    System.out.println("This user is available in the group!!!");
                    return;
                }
                group.getMembers().add(user1);
                isFound = true;
                break;
            }
        }

        if (isFound){
            System.out.println("Successfully added!!!");
        }else {
            System.out.println("User not found!!!");
        }


    }

    private void showGroupChat(Group group, User user) {
        if(group.getGroupMessageList().size() == 0){
            System.out.println("No messages yet!!!");
        }

        for (Message message : group.getGroupMessageList()) {
            System.out.println(message.getSenderName() + ": " + message.getBody());
        }

        while (true) {
            System.out.println("Type (0=>back): ");
            String body = scannerStr.nextLine();

            if(body.equals("0")){break;}

            Message message = new Message((int) (Math.random() * 10000), body, user.getUserName(), user.getFullName());
            group.getGroupMessageList().add(message);
        }

    }
}
