package uz.pdp.producer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.producer.dto.MyDto;
import uz.pdp.producer.service.ProducerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/producer")
public class ProducerController {

    private final ProducerService producerService;

//    private final RabbitMQSender rabbitMqSender;


    @PostMapping("/publish")
    public String publish(@RequestBody MyDto myDto) {
        producerService.save(myDto);
        return "Saved succesfully...";
    }

}
