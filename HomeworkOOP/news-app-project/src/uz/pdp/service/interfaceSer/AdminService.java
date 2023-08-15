package uz.pdp.service.interfaceSer;

import uz.pdp.model.News;
import uz.pdp.model.payment.PaymentMethod;
import uz.pdp.model.payment.Transaction;
import uz.pdp.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AdminService {

    void showAdminMenu(Map<String, User> userMap, List<News> newsList, User admin,List<Transaction> transactionList, List<PaymentMethod> paymentMethods);

    void showJournalistList(Collection<User> journalistList);

    void checkNews(List<News> newsList, User admin,List<Transaction> transactionList, List<PaymentMethod> paymentMethods);

    void transactionList(List<Transaction> transactionList);


    void deposit(User user);


}
