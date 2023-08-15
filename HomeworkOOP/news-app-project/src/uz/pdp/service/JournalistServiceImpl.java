package uz.pdp.service;

import uz.pdp.model.News;
import uz.pdp.model.User;
import uz.pdp.model.enums.Status;
import uz.pdp.service.interfaceSer.JournalistService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JournalistServiceImpl implements JournalistService {
    static UserServiceImpl userService = new UserServiceImpl();
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    static MessageServiceImpl messageService = new MessageServiceImpl();

    @Override
    public void showJournalistMenu(List<News> newsList, User journalist, Map<String, User> userMap) {
        while (true) {
            System.out.println("1=> Add news, 2=> My news, 3=> All news, 5.Send Message, 6.Connections, 4=>Balance 0=> Logout");
            int option = scannerInt.nextInt();
            switch (option) {
                case 1:
                    addNews(newsList, journalist);
                    break;
                case 2:
                    myNews(newsList, journalist);
                    break;
                case 3:
                    userService.showAllNews(newsList, journalist);
                    break;
                case 4:
                    userService.checkBalance(journalist);
                    break;
                case 5:
                    messageService.sendMessage(journalist, userMap.values());
                    break;
                case 6:
                    messageService.messageUser(journalist, userMap.values());
                    break;
                case 0:
                    return;
            }
        }
    }

    @Override
    public void addNews(List<News> newsList, User journalist) {

        System.out.println("Enter title");
        String title = scannerStr.nextLine();

        System.out.println("Enter description");
        String description = scannerStr.nextLine();
        if (journalist.getBlockCount() >= 3) {
            System.out.println("You are in block, send message admin");
            return;
        }
        newsList.add(new News((int) (Math.random() * 10000), title, description, journalist, Status.NEW));


        System.out.println("News successfully created");
    }

    @Override
    public void myNews(List<News> newsList, User journalist) {
        newsList.stream().filter(news -> news.getAuthor().equals(journalist)).forEach(news -> {
            System.out.println("id: " + news.getId() + "\n" +
                    "author: " + news.getAuthor().getFullName() + "\n" +
                    "title: " + news.getTitle() + "\n" +
                    "stats : " + news.getStatus() + "\n" +
                    "=====================================");
        });
    }
}
