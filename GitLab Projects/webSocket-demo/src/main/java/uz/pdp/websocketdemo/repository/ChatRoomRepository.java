package uz.pdp.websocketdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.websocketdemo.model.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,  Integer> {

    Optional<ChatRoom> findBySenderIdAndReceiverId(Integer sender_id, Integer receiver_id);

}
