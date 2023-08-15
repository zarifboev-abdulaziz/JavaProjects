package uz.pdp.consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import uz.pdp.consumer.dto.MyDto;

@Service
public class RabbitMqReceiver {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(MyDto myDto) {
        System.out.println("Received... " + myDto);
        // TODO: 4/22/2022 write your logic here...
    }

}