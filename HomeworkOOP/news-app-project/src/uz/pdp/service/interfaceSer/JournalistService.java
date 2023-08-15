package uz.pdp.service.interfaceSer;

import uz.pdp.model.News;
import uz.pdp.model.User;

import java.util.List;
import java.util.Map;

public interface JournalistService {

    void showJournalistMenu(List<News> newsList, User journalist, Map<String, User> userMap);

    void addNews(List<News> newsList, User journalist);

    void myNews(List<News> newsList, User journalist);


}
