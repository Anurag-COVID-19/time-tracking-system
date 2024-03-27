package com.punch.punchmanagementservice.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InputRequest {
    private Long id;
    private String timestamp;
    private SwipeType swipeType;
}
