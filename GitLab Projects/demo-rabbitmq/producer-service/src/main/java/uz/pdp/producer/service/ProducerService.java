package uz.pdp.producer.service;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.pdp.producer.dto.MyDto;


@Service
@RequiredArgsConstructor
public class ProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void save(MyDto myDto) {

        // TODO: 4/22/2022 your logic here...

        //sending to consumer...
        rabbitTemplate.convertAndSend(exchange, routingkey, myDto);


        // TODO: 4/22/2022 saved...
        // TODO: 4/22/2022 return response to controller

    }


}

