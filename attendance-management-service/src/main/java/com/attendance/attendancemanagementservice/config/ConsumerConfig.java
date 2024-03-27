package com.attendance.attendancemanagementservice.config;

import com.attendance.attendancemanagementservice.entity.SwipeEvent;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EnableKafka
@Configuration
public class ConsumerConfig
{

    @Value("${spring.kafka.consumer.group-id.name}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, SwipeEvent> createSwipeEventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.CLIENT_ID_CONFIG, "ATTENDANCE"+Math.incrementExact(1));
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.punch.punchmanagementservice.entity.SwipeEvent.class");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(SwipeEvent.class)));
    }

    @Bean("swipeEventKafkaListenerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, SwipeEvent> swipeEventKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SwipeEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(createSwipeEventConsumerFactory());
        factory.getContainerProperties().setAckCount(ContainerProperties.AckMode.MANUAL_IMMEDIATE.ordinal());
        factory.setCommonErrorHandler(new DefaultErrorHandler());
        return factory;
    }


}
