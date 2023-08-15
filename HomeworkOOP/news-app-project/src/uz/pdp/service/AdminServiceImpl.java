package uz.pdp.service;


import uz.pdp.Main;
import uz.pdp.model.News;
import uz.pdp.model.User;
import uz.pdp.model.enums.Status;
import uz.pdp.model.payment.PaymentMethod;
import uz.pdp.model.payment.Transaction;
import uz.pdp.service.interfaceSer.AdminService;
import uz.pdp.service.interfaceSer.PaymentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdminServiceImpl implements AdminService, PaymentService {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerDou = new Scanner(System.in);

    static UserServiceImpl userService = new UserServiceImpl();
    static MessageServiceImpl messageService = new MessageServiceImpl();


    @Override
    public void showAdminMenu(
            Map<String, User> userMap,
            List<News> newsList,
            User admin,
            List<Transaction> transactionList,
            List<PaymentMethod> paymentMethods
    ) {

        while (true) {


            System.out.println("1=> Show journalist list, 2=> Check news, 3=> Check balance, 4=>Deposit, 5=>Transaction History,\n" +
                    "6=>Add Payment, 7=>EditPaYm, 8=>DelePay, 9=>Show PayN, \n" +
                    "10=>Blocked journalist, 11=>SendMessage, 12=>Connections, 0=> Logout");
            int option = scannerInt.nextInt();
            switch (option) {
                case 1:
                    showJournalistList(userMap.values());
                    break;
                case 2:
                    checkNews(newsList, admin, transactionList, paymentMethods);
                    break;
                case 3:
                    userService.checkBalance(admin);
                    break;
                case 4:
                    deposit(admin);
                    break;
                case 5:
                    transactionList(transactionList);
                    break;
                case 6:
                    addPaymentMethod(paymentMethods);
                    break;
                case 7:
                    editPaymentMethod(paymentMethods);
                    break;
                case 8:
                    deletePaymentMethod(paymentMethods);
                    break;
                case 9:
                    showPaymentMethod(paymentMethods);
                    break;
                case 10:
                    unblockUser(userMap.values());
                    break;
                case 11:
                    messageService.sendMessage(admin, userMap.values());
                    break;
                case 12:
                    messageService.messageUser(admin, userMap.values());
                    break;
                case 0:
                    return;
            }
        }
    }

    public void blockJournalist(Collection<User> journalistList) {
        for (User user : journalistList) {
            if (user.getBlockCount() >= 3) {
                System.out.println("id: " + user.getId() + "\n" +
                        "full name: " + user.getFullName() + "\n" +
                        "email: " + user.getEmail() + "\n" +
                        "block : " + "blocked" +
                        "=====================================");
            }

        }
    }

    public void unblockUser(Collection<User> journalistList) {
        blockJournalist(journalistList);
        System.out.println("Enter journalist id");
        int userId = scannerInt.nextInt();
        User selectedUser = journalistList.stream()
                .filter(user -> user.getId() == userId).findFirst().orElse(null);
        if (selectedUser != null) {
            selectedUser.setBlockCount(0);
            System.out.println("Successfully unblock");
            return;
        }
        System.out.println("Not found user");
    }

    @Override
    public void showJournalistList(Collection<User> journalistList) {
        for (User user : journalistList) {
            System.out.println("id: " + user.getId() + "\n" +
                    "full name: " + user.getFullName() + "\n" +
                    "email: " + user.getEmail() + "\n" +
                    "=====================================");

        }
    }

    @Override
    public void checkNews(List<News> newsList, User admin,
                          List<Transaction> transactionList,
                          List<PaymentMethod> paymentMethods) {
        while (true) {
            for (News news : newsList) {
                if (news.getStatus().equals(Status.NEW)) {
                    System.out.println("id: " + news.getId() + "\n" +
                            "author: " + news.getAuthor().getFullName() + "\n" +
                            "title: " + news.getTitle() + "\n" +
                            "=====================================");
                }
            }
            System.out.println("Input news id (0=> Back): ");
            int inputNewsId = scannerInt.nextInt();
            if (inputNewsId == 0) return;
            News selectedNews = null;
            for (News news : newsList) {
                if (news.getId() == inputNewsId) {
                    selectedNews = news;
                    break;
                }
            }
            if (selectedNews != null) {
                System.out.println("id: " + selectedNews.getId() + "\n" +
                        "author: " + selectedNews.getAuthor().getFullName() + "\n" +
                        "title: " + selectedNews.getTitle() + "\n" +
                        "body: " + selectedNews.getBody());
                System.out.println("1=> Accept, 2=> Reject, 0=> Back");
                int option = scannerInt.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Choose Payment method");
                        paymentMethodList(paymentMethods);
                        int payOpt = scannerInt.nextInt();
                        PaymentMethod selectedPayment = choosePayment(payOpt, paymentMethods);
                        if (selectedPayment != null) {
                            System.out.println("Enter amount you want to give");
                            int amount = scannerInt.nextInt();
                            double total = amount + amount * selectedPayment.getCommissionFee() / 100;
                            if (admin.getBalance() < total) {
                                System.out.println("You do not have enough money!!");
                                break;
                            }

                            double authorBalance = selectedNews.getAuthor().getBalance();
                            selectedNews.getAuthor().setBalance(authorBalance + amount);
                            admin.setBalance(admin.getBalance() - total);

                            transactionList.add(new Transaction((int) (Math.random() * 10000), amount, selectedNews, selectedPayment));
                            selectedNews.setStatus(Status.ACCEPTED);
                            System.out.println("======================================");
                            System.out.println(selectedNews.getTitle() + " successfully accepted!!!");
                            System.out.println("======================================");

                        } else {
                            System.out.println("PaymentMethod Not found and Acception failed");
                        }
                        return;
                    case 2:
                        selectedNews.setStatus(Status.REJECTED);
                        selectedNews.getAuthor().addBlockCount(selectedNews.getAuthor(), 1);
                        System.out.println("======================================");
                        System.out.println(selectedNews.getTitle() + " rejected!!!");
                        System.out.println("======================================");
                        break;
                    case 0:
                        return;
                }
            }

        }
    }

    private PaymentMethod choosePayment(int payOpt, List<PaymentMethod> paymentMethods) {

        for (PaymentMethod paymentMethod : paymentMethods) {
            if (paymentMethod.getId() == payOpt)
                return paymentMethod;
        }

        return null;
    }

    @Override
    public void transactionList(List<Transaction> transactionList) {
        transactionList.stream().forEach(transaction -> {
            System.out.println("id : " + transaction.getId());
            System.out.println("Type : " + transaction.getType().getName().toUpperCase());
            System.out.println("Fee : " + transaction.getType().getCommissionFee());
            System.out.println("Amount : " + transaction.getAmount());
            System.out.println("Total : " + ((transaction.getAmount() * transaction.getType().getCommissionFee() / 100) + transaction.getAmount()));
            System.out.println("Jurnalist : " + transaction.getNews().getAuthor().getFullName());
            System.out.println("News : " + transaction.getNews().getTitle());
            System.out.println("============================================");
        });
    }

    @Override
    public void deposit(User user) {
        System.out.println("Enter amount");
        int amount = scannerInt.nextInt();
        if (amount > 0) user.setBalance(user.getBalance() + amount);
    }

    @Override
    public void paymentMethodList(List<PaymentMethod> paymentMethods) {
        paymentMethods.stream().forEach(
                paymentMethod -> {
                    System.out.println("id : " + paymentMethod.getId() +
                            " ,Method :" + paymentMethod.getName().toUpperCase() +
                            " , fee : " + paymentMethod.getCommissionFee());
                }
        );
    }

    @Override
    public void addPaymentMethod(List<PaymentMethod> paymentMethods) {
        System.out.println("Enter payment method");
        String method = scannerStr.nextLine();

        System.out.println("Enter commission fee");
        double fee = scannerDou.nextDouble();

        if (!checkExist(paymentMethods, method)) {
            paymentMethods.add(new PaymentMethod((int) (Math.random() * 10000), method, fee));
            System.out.println("Successfully created");
        } else {
            System.out.println(method + " alredyexist");
        }
    }

    private boolean checkExist(List<PaymentMethod> paymentMethods, String name) {
        for (PaymentMethod paymentMethod : paymentMethods)
            if (paymentMethod.getName().equalsIgnoreCase(name)) return true;
        return false;
    }


    @Override
    public void editPaymentMethod(List<PaymentMethod> paymentMethods) {
        showPaymentMethod(paymentMethods);
        System.out.println("Enter id edit");
        int enid = scannerInt.nextInt();
        PaymentMethod paymentMethod = findPayMethod(paymentMethods, enid);
        if (paymentMethod != null) {
            System.out.println("Enter fee");
            double fee = scannerDou.nextDouble();
            if (fee > 0) {
                paymentMethod.setCommissionFee(fee);
                System.out.println("Succcess");
                return;
            }
            System.out.println("Failed");

        } else {
            System.out.println("Not found");
        }
    }

    private PaymentMethod findPayMethod(List<PaymentMethod> paymentMethods, int enid) {
        return paymentMethods.stream().filter(paymentMethod -> paymentMethod.getId() == enid).findFirst().orElse(null);
    }

    @Override
    public void showPaymentMethod(List<PaymentMethod> paymentMethods) {
        paymentMethods.stream().forEach(
                paymentMethod -> {
                    System.out.println("id : " + paymentMethod.getId() +
                            " ,Method :" + paymentMethod.getName().toUpperCase() +
                            " , fee : " + paymentMethod.getCommissionFee());
                }
        );

    }

    @Override
    public void deletePaymentMethod(List<PaymentMethod> paymentMethods) {
        showPaymentMethod(paymentMethods);
        System.out.println("Enter id del");
        int enid = scannerInt.nextInt();
        PaymentMethod paymentMethod = findPayMethod(paymentMethods, enid);
        if (paymentMethod != null) {
            paymentMethods.remove(paymentMethod);
            System.out.println("Succcess");
            return;
        }
        System.out.println("Not found");

    }
}
