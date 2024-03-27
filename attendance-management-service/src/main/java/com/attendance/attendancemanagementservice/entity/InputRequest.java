package com.attendance.attendancemanagementservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputRequest
{
    private Long employeeId;
    private String currentDate;
}
