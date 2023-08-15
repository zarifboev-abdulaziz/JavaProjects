import javax.jws.soap.SOAPBinding;

public interface UserService {

    User login();

    void register();

    void hisobniToldirish(User user);

    void kartaQoshish(User user);

    void kartaniOchirish(User user);

    void tariflar(User user);

    void balansniKorish(User user);

    void profilim(User user);




}
