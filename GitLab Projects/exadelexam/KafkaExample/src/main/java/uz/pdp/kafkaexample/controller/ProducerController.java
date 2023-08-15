package uz.pdp.kafkaexample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.kafkaexample.dto.UserDto;

@RestController
@RequestMapping("/api/producer")
@RequiredArgsConstructor
public class ProducerController {
    private final KafkaTemplate kafkaTemplate;
    @Value("${spring.kafka.bootstrap-servers}")
    String bootstrapServer;

    @PostMapping
    public ResponseEntity<?> publish(@RequestBody UserDto userDto){
        kafkaTemplate.send("myNewTopic", userDto);
        return ResponseEntity.ok("qwerty done .... ..... . .. . .");
    }

}
