package com.attendance.attendancemanagementservice.controller;

import com.attendance.attendancemanagementservice.entity.InputRequest;
import com.attendance.attendancemanagementservice.service.EODService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ComputeController {

    @Autowired
    private EODService eodService;

    @PostMapping("/attendance")
    public ResponseEntity<String> compute(@RequestBody InputRequest inputRequest) {

        String status = eodService.eodCalculation(inputRequest);
        return ResponseEntity.ok(status);
    }
}
