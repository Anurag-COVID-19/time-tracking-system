package com.punch.punchmanagementservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SwipeEvent {

    private Long employeeId;
    private LocalDateTime timestamp;
    private SwipeType swipeType;
}
