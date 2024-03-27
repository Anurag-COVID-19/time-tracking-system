package com.attendance.attendancemanagementservice.repository;

import com.attendance.attendancemanagementservice.entity.SwipeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwipeEventRepository extends JpaRepository<SwipeEvent, String> {
}
