package uz.pdp.consumerapp.controller;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.consumerapp.dto.UserDto;

@Service
@RequiredArgsConstructor
public class ConsumerController {

    @KafkaListener(topics = "myNewTopic", groupId = "myGroupId")
    public void receiveMessage(UserDto userDto) {
        System.out.println("Received... " + userDto);
        // TODO: 4/22/2022 write your logic here...
    }


}
