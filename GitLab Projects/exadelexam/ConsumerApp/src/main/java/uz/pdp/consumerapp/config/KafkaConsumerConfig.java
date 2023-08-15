package uz.pdp.consumerapp.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import uz.pdp.consumerapp.dto.UserDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public KafkaListenerContainerFactory<
            ConcurrentMessageListenerContainer<
                    String, UserDto>>
    factory() {
        ConcurrentKafkaListenerContainerFactory<String, UserDto> containerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory());
        return containerFactory;
    }

    @Bean
    public ConsumerFactory<String, UserDto> consumerFactory() {

        JsonDeserializer<UserDto> userDtoJsonDeserializer =
                new JsonDeserializer<>(UserDto.class,false);

        userDtoJsonDeserializer.addTrustedPackages("uz.pdp.common");

        return new DefaultKafkaConsumerFactory<>(
                configs(),
                new StringDeserializer(),
                userDtoJsonDeserializer
        );
    }

    @Bean
    public Map<String, Object> configs() {

        HashMap<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"1");
        return props;
    }
}


