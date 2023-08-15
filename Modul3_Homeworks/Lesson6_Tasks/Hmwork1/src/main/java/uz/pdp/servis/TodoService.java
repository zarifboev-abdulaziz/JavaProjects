package uz.pdp.servis;

import uz.pdp.DataBase;
import uz.pdp.model.Task;
import uz.pdp.model.User;

import java.util.Scanner;

public class TodoService {
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);


    public void todoMenu(User user){
        System.out.println("1.Show todo List\n2.Add todo\n3.Update todo\n4.Delete todo\n5.Search todo by title\n6.Log out");
        int option = scannerInt.nextInt();
        switch (option){
            case 1:
                showTodoList(user);
                break;
            case 2:
                addTodo(user);
                break;
            case 3:
                updateTodo(user);
                break;
            case 4:
                deleteTodo(user);
                break;
            case 5:
                searchTodoByTitle(user);
                break;

            case 6: return;
            default:
                System.err.println("Wrong option");
                break;
        }

        todoMenu(user);
    }

    private void searchTodoByTitle(User user) {
        System.out.println("Enter title : ");
        String title = scannerStr.nextLine();

        for (Task task : DataBase.taskList) {
            if (task.getUserId() == user.getId() && task.getTitle().equalsIgnoreCase(title)){
                String status = task.getCompleted() ? "Completed" : "Not Completed";
                System.out.println("Title: " + task.getTitle() + "Status: " + status);
            }
        }

    }

    private void deleteTodo(User user) {
        for (Task task : DataBase.taskList) {
            if (task.getUserId() == user.getId()){
                System.out.println("Todo: " + task.getTitle() + "Id: "  +task.getId());
            }
        }

        System.out.println("Enter todo id to select: ");
        long inputid = scannerInt.nextLong();

        DataBase.taskList.removeIf(task -> task.getUserId() == user.getId() && task.getId() == inputid);

        System.out.println("Successfully Removed!!!");
    }

    private void updateTodo(User user) {
        for (Task task : DataBase.taskList) {
            if (task.getUserId() == user.getId()){
                System.out.println("Todo: " + task.getTitle() + "Id: "  +task.getId());
            }
        }

        System.out.println("Enter todo id to select: ");
        long inputid = scannerInt.nextLong();

        Task selectedTask = null;
        for (Task task : DataBase.taskList) {
            if (task.getUserId() == user.getId() && task.getId() == inputid){
                selectedTask = task;
            }
        }

        System.out.println("Enter new title: ");
        String title = scannerStr.nextLine();

        boolean status = false;
        System.out.println("Decide status!\n1.Completed\n2.Not Competed");
        int option = scannerInt.nextInt();

        if (option == 1){
            status = true;
        }

        selectedTask.setTitle(title);
        selectedTask.setCompleted(status);

        System.out.println("Successfully updated!!!");
    }

    private void addTodo(User user) {
        long id = (long) (Math.random()*10000);
        System.out.println("Enter title: ");
        String title = scannerStr.nextLine();

        Task task = new Task(false, id, title, user.getId());
        DataBase.taskList.add(task);
        System.out.println("Successful operation!!!");

    }

    private void showTodoList(User user) {
        for (Task task : DataBase.taskList) {
            if (task.getUserId() == user.getId()){
                String status = task.getCompleted() ? "Completed" : "Not Completed";
                System.out.println("Title: " + task.getTitle() + ", Status: " + status);
            }
        }
    }


}
