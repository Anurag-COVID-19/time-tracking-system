package com.attendance.attendancemanagementservice.listener;

import com.attendance.attendancemanagementservice.entity.SwipeEvent;
import com.attendance.attendancemanagementservice.service.SwipeEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;

@Service
public class SwipeEventListener
{
    private static final Logger logger = LoggerFactory.getLogger(SwipeEventListener.class.getSimpleName());

    @Autowired
    private SwipeEventService eventService;

    @Autowired
    private ObjectMapper mapper;

    @KafkaListener(topics = "swipe-event", containerFactory = "swipeEventKafkaListenerFactory")
    public void eventListener(@Payload SwipeEvent record) throws IOException {
        logger.info("-----Inside consumer-----"+ record.toString());
        SwipeEvent swipeEvent = mapper.convertValue(record, SwipeEvent.class);
        eventService.persist(swipeEvent);
        //acknowledgment.acknowledge();
    }
}
