package com.attendance.attendancemanagementservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SwipeEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "time_period")
    private LocalDateTime timestamp;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Swipe_type")
    private SwipeType swipeType;
}
