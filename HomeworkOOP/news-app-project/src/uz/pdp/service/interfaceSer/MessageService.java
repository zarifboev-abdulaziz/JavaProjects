package uz.pdp.service.interfaceSer;

import uz.pdp.model.User;

import java.util.Collection;
import java.util.Map;

public interface MessageService {

    void sendMessage(User journalist, Collection<User> journalistList);

    void messageUser(User journalist, Collection<User> journalistList);

}
