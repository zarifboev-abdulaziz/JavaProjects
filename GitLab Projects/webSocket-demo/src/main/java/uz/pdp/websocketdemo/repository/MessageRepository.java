package uz.pdp.websocketdemo.repository;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.websocketdemo.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> getAllByReceiverId(Integer receiverId);


    List<Message> findAllByChatId(String chatId);

}
