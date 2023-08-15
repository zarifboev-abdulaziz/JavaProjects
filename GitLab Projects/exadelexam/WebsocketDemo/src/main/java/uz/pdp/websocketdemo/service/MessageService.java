package uz.pdp.websocketdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.websocketdemo.dto.MessageDto;
import uz.pdp.websocketdemo.model.Message;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.repository.MessageRepository;
import uz.pdp.websocketdemo.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;


    public void sendMessage(String to, MessageDto message) {

//        jdbcTemplate.update("insert into messages (message_text,message_from,message_to,created_datetime) " +
//                "values (?,?,?,current_time )",message.getMessage(),message.getFromLogin(),to);

        Optional<User> optionalMessageFromUser = userRepository.findById(message.getFromLogin());
        if (!optionalMessageFromUser.isPresent()) return;
        User messageFrom = optionalMessageFromUser.get();

        Optional<User> optionalMessageToUser = userRepository.findById(Integer.parseInt(to));
        if (!optionalMessageToUser.isPresent()) return;
        User messageTo = optionalMessageToUser.get();


        Message savingMessage = new Message(null, message.getMessage(), messageFrom, messageTo, Timestamp.valueOf(LocalDateTime.now()));
        messageRepository.save(savingMessage);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
    }

    public List<Map<String,Object>> getListMessage(@PathVariable("from") Integer from, @PathVariable("to") Integer to){

//        return jdbcTemplate.queryForList("select * from messages where (message_from=? and message_to=?) " +
//                "or (message_to=? and message_from=?) order by created_datetime asc",from,to,from,to);

        List<Message> allMessageOfUser = messageRepository.getAllMessageOfUser(from, to);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Message message : allMessageOfUser) {

            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put(message.getId().toString(), message);
            result.add(objectMap);
        }
        return result;
    }



}
