package uz.pdp.websocketdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.websocketdemo.model.ChatRoom;
import uz.pdp.websocketdemo.model.Message;
import uz.pdp.websocketdemo.model.MessageStatus;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.payload.MessageDto;
import uz.pdp.websocketdemo.repository.ChatRoomRepository;
import uz.pdp.websocketdemo.repository.MessageRepository;
import uz.pdp.websocketdemo.repository.UserRepository;
import uz.pdp.websocketdemo.service.ChatService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatService chatService;


    @GetMapping("/{receiverId}")
    public ResponseEntity<?> getAllMessages(@PathVariable Integer receiverId, @AuthenticationPrincipal User currentUser) {

        Optional<User> optionalReceiver = userRepository.findById(receiverId);
        if (optionalReceiver.isEmpty()) return null;

        List<Message> all = messageRepository.findAllByChatId(chatService.getChatId(currentUser, optionalReceiver.get()));
        for (Message message : all) {
            if (message.getSender().getId() == currentUser.getId()) continue;
            message.setMessageStatus(MessageStatus.READ);
            messageRepository.save(message);
        }

        List<MessageDto> messageDtoList = all.stream().map(message -> new MessageDto(
                message.getId(),
                message.getText(),
                message.getReceiver().getId(),
                message.getSender().getId(),
                message.getSender().getFullName(),
                message.getReceiver().getFullName(),
                message.getLocalDateTime(),
                message.getMessageStatus()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(messageDtoList);
    }

}
