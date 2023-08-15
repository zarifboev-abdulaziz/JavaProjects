package uz.pdp.websocketdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.websocketdemo.model.ChatRoom;
import uz.pdp.websocketdemo.model.User;
import uz.pdp.websocketdemo.repository.ChatRoomRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;

    public String getChatId(User sender, User receiver) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findBySenderIdAndReceiverId(sender.getId(), receiver.getId());
        if (optionalChatRoom.isPresent()) {
            return optionalChatRoom.get().getChatId();
        }

        String uuid = UUID.randomUUID().toString();
        chatRoomRepository.save(new ChatRoom(uuid, sender, receiver));
        chatRoomRepository.save(new ChatRoom(uuid, receiver, sender));
        return uuid;
    }

}
