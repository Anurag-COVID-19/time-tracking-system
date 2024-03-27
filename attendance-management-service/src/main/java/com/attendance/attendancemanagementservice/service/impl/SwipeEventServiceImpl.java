package com.attendance.attendancemanagementservice.service.impl;

import com.attendance.attendancemanagementservice.entity.SwipeEvent;
import com.attendance.attendancemanagementservice.repository.SwipeEventRepository;
import com.attendance.attendancemanagementservice.service.SwipeEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SwipeEventServiceImpl implements SwipeEventService {

    private static final Logger logger = LoggerFactory.getLogger(SwipeEventServiceImpl.class.getSimpleName());

    @Autowired
    private SwipeEventRepository repository;

    @Override
    public void persist(SwipeEvent swipeEvent) {
        logger.debug("Initiated input validation and persistence");
        swipeEvent.setId(UUID.randomUUID().toString());
        if (!SwipeEventValidator.validate(swipeEvent)) throw new RuntimeException("Validation failed..."+ swipeEvent.toString());
        repository.save(swipeEvent);
        logger.debug("Completed input validation and persistence");
    }

    private static class SwipeEventValidator {

        public static boolean validate(SwipeEvent swipeEvent) {
            if (swipeEvent == null) return false;
            if (swipeEvent.getEmployeeId() == null) return false;
            if (swipeEvent.getTimestamp() == null)  return false;
            return swipeEvent.getSwipeType() != null;
        }
    }
}
