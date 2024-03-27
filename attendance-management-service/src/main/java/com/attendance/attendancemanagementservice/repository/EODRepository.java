package com.attendance.attendancemanagementservice.repository;

import com.attendance.attendancemanagementservice.entity.SwipeEvent;
import com.attendance.attendancemanagementservice.entity.SwipeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface EODRepository extends JpaRepository<SwipeEvent, Long> {

    @Query("select MIN(timestamp) FROM SwipeEvent u WHERE u.timestamp BETWEEN :startDate AND :endDate AND u.employeeId = :employee_id AND u.swipeType = :swipe_type order by u.swipeType desc")
    LocalDateTime findMinTimePeriod(
            Long employee_id,
            SwipeType swipe_type,
            LocalDateTime startDate,
            LocalDateTime endDate);

    @Query("select MAX(timestamp) FROM SwipeEvent u WHERE u.timestamp BETWEEN :startDate AND :endDate AND u.employeeId = :employee_id AND u.swipeType = :swipe_type order by u.swipeType desc")
    LocalDateTime findMaxTimePeriod(
            Long employee_id,
            SwipeType swipe_type,
            LocalDateTime startDate,
            LocalDateTime endDate);
}
