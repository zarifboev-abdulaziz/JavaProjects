package uz.pdp;

import uz.pdp.model.User;
import uz.pdp.service.AbiturientServiceImpl;
import uz.pdp.service.AdminServiceImpl;
import uz.pdp.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        AdminServiceImpl adminService = new AdminServiceImpl();
        AbiturientServiceImpl abiturientService = new AbiturientServiceImpl();

        System.out.println("Welcome to online test App");
        while (true){
            System.out.println("1.Log in\n2.Register\n3.Exit");
            int option = DB.scannerInt.nextInt();
            switch (option){
                case 1:
                    User user = userService.login();
                    if(user != null){
                        switch (user.getRole()){
                            case ADMIN:
                                adminService.adminMenu(user);
                                break;
                            case ABITURIENT:
                                abiturientService.abiturientMenu(user);
                                break;
                        }
                    }
                    break;
                case 2:
                    userService.register();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Wrong option");
                    break;
            }

        }
    }
}
