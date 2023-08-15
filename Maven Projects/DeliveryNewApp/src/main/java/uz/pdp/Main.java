package uz.pdp;

import uz.pdp.service.mainMenu.MainMenuServiceImpl;

public class Main {
    public static void main(String[] args) {
        MainMenuServiceImpl mainMenuService = new MainMenuServiceImpl();
        mainMenuService.mainMenu();

    }
}
