package uz.pdp.websocketdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import uz.pdp.websocketdemo.model.ChatRoom;
import uz.pdp.websocketdemo.model.Message;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.payload.MessageDto;
import uz.pdp.websocketdemo.repository.ChatRoomRepository;
import uz.pdp.websocketdemo.repository.MessageRepository;
import uz.pdp.websocketdemo.repository.UserRepository;
import uz.pdp.websocketdemo.service.ChatService;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/hello")
//    @SendTo("/topic/chat")
    public void sendMessage(MessageDto messageDto) throws Exception {

        Optional<User> receiverUser = userRepository.findById(messageDto.getReceiverId());
        Optional<User> senderUser = userRepository.findById(messageDto.getSenderId());

        if (senderUser.isEmpty() || receiverUser.isEmpty()) throw new RuntimeException();

        Message savedMessage = messageRepository.save(
                new Message(
                        messageDto.getText(),
                        receiverUser.get(),
                        senderUser.get(),
                        chatService.getChatId(senderUser.get(), receiverUser.get())
                )
        );

        messageDto.setId(savedMessage.getId());
//        messageDto.setDate(savedMessage.getCreatedAt());
//        messageDto.setStatus(savedMessage.getStatus());

        // user/2/queue/messages
        messagingTemplate.convertAndSendToUser(
                messageDto.getReceiverId().toString(),
                "/queue/messages",
                messageDto
        );

        if (messageDto.getSenderId() == messageDto.getReceiverId()) return;

        messagingTemplate.convertAndSendToUser(
                messageDto.getSenderId().toString(),
                "/queue/messages",
                messageDto
        );
    }




}
