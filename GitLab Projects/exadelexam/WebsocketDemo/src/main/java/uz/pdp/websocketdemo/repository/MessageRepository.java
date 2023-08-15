package uz.pdp.websocketdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.websocketdemo.model.Message;
import uz.pdp.websocketdemo.model.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {


    @Query(nativeQuery = true, value = "select * \n" +
            "from messages m \n" +
            "where (m.message_from_id=:from and m.message_to_id=:to) \n" +
            "or (m.message_to_id=:from and m.message_from_id=:to) order by m.created_at asc")
    List<Message> getAllMessageOfUser(Integer from, Integer to);
}
