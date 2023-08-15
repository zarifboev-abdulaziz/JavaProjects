package uz.pdp.service.interfaceSer;

import uz.pdp.model.News;
import uz.pdp.model.payment.PaymentMethod;
import uz.pdp.model.payment.Transaction;
import uz.pdp.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    void login(Map<String, User> userMap, List<News> newsList, List<Transaction> transactionList, List<PaymentMethod> paymentMethods);

    void register(Map<String, User> userMap);

    void checkBalance(User user);

    void showAllNews(List<News> newsList, User user);

}
