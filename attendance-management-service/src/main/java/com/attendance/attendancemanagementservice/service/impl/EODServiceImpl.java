package com.attendance.attendancemanagementservice.service.impl;

import com.attendance.attendancemanagementservice.entity.InputRequest;
import com.attendance.attendancemanagementservice.entity.SwipeType;
import com.attendance.attendancemanagementservice.repository.EODRepository;
import com.attendance.attendancemanagementservice.service.EODService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class EODServiceImpl implements EODService {

    private static final Logger logger = LoggerFactory.getLogger(EODServiceImpl.class.getName());

    @Autowired
    private EODRepository repository;

    @Override
    public String eodCalculation(InputRequest request) {

        return formula(request);
    }

    private String formula(InputRequest request) {

        int net_timing = (int) getNetTiming(request);

        if (net_timing < 4)  { return "ABSENT"; }
        else if (net_timing > 4 && net_timing < 8) { return "HALF_DAY"; }
        else if (net_timing >= 8) { return "PRESENT"; }
        else return "NOT_APPLICABLE";

    }

    private long getNetTiming(InputRequest request) {
        LocalDateTime endDate = LocalDateTime.MAX.toLocalTime().atDate(LocalDate.parse(request.getCurrentDate()));
        LocalDateTime startDate = LocalDateTime.MIN.toLocalTime().atDate(LocalDate.parse(request.getCurrentDate()));
        return getNet_timing(request.getEmployeeId(), startDate, endDate);
    }

    private long getNet_timing(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime in_timing = calculateOfficeInTiming(id, startDate, endDate);
        LocalDateTime out_timing = calculateOfficeOutTiming(id, startDate, endDate);

        LocalTime startTiming = in_timing.toLocalTime();
        LocalTime endTiming = out_timing.toLocalTime();
        return Duration.between(endTiming, startTiming).toHours();
    }

    private LocalDateTime calculateOfficeInTiming(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        logger.info("StartTime: "+ startDate+ " EndDateTime "+ endDate+ " Type "+ SwipeType.IN);
        LocalDateTime in_timing = repository.findMinTimePeriod(id,
                SwipeType.IN,
                startDate,
                endDate);
        logger.info("Debug :: "+in_timing);
        return in_timing;
    }

    private LocalDateTime calculateOfficeOutTiming(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        logger.info("StartTime: "+ startDate+ " EndDateTime "+ endDate+ " Type "+ SwipeType.IN);
        LocalDateTime out_timing = repository.findMaxTimePeriod(id,
                SwipeType.OUT,
                startDate,
                endDate);
        logger.info("Debug :: "+out_timing);
        return out_timing;
    }
}
