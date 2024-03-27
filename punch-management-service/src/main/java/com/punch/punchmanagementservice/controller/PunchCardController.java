package com.punch.punchmanagementservice.controller;

import com.punch.punchmanagementservice.entity.InputRequest;
import com.punch.punchmanagementservice.service.PublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/register")
public class PunchCardController {

    private static final Logger logger = LoggerFactory.getLogger(PunchCardController.class.getSimpleName());
    @Autowired
    private PublishService service;

    @PostMapping("/swipe")
    public ResponseEntity<?> swipe(@RequestBody InputRequest request) {
        logger.info("Request initiated");
        service.processRecord(request);
        logger.info("Request completed");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
