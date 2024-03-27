package com.punch.punchmanagementservice.service.impl;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.punch.punchmanagementservice.entity.InputRequest;
import com.punch.punchmanagementservice.entity.SwipeEvent;
import com.punch.punchmanagementservice.service.PublishService;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class PublishServiceImpl implements PublishService {

    private static final Logger logger = LoggerFactory.getLogger(PublishServiceImpl.class.getSimpleName());

    /*@Autowired
    private SwipeRepository repository;*/

    @Autowired
    private KafkaTemplate<?, SwipeEvent> kafkaTemplate;

    @Override
    public void processRecord(InputRequest request) {

        SwipeEvent swipeEvent = getSwipeEvent(request);

        //repository.save(swipeEvent);

        CompletableFuture<? extends SendResult<?, SwipeEvent>> future = kafkaTemplate.send("swipe-event", swipeEvent);
        try {
            SendResult<?, SwipeEvent> data = future.get();
            if (data != null) {
                logger.info("Received metadata \n"+
                        "Topic: "+ data.getRecordMetadata().topic()+ "\n"+
                        "Partition: "+ data.getRecordMetadata().partition()+ "\n"+
                        "Offset: "+ data.getRecordMetadata().offset()+ "\n"+
                        "Timestamp: "+ data.getRecordMetadata().timestamp());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static SwipeEvent getSwipeEvent(InputRequest request) {
        SwipeEvent swipeEvent = new SwipeEvent();
        swipeEvent.setEmployeeId(request.getId());
        LocalDateTime formattedDate = getLocalDateTime(request);
        swipeEvent.setTimestamp(formattedDate);
        swipeEvent.setSwipeType(request.getSwipeType());
        return swipeEvent;
    }

    private static LocalDateTime getLocalDateTime(InputRequest request) {
        DateTimeFormatter format = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(request.getTimestamp(), format);
        return offsetDateTime.toLocalDateTime();
    }

    /**
     * if (e == null) {
     *                     logger.info("Recieved metadata \n"+
     *                             "Topic: "+ recordMetadata.topic()+ "\n"+
     *                             "Partition: "+ recordMetadata.partition()+ "\n"+
     *                             "Offset: "+ recordMetadata.offset()+ "\n"+
     *                             "Timestamp: "+ recordMetadata.timestamp());
     *                 } else {
     *                     logger.error("Error while producing"+ e);
     *                 }
     */
}
