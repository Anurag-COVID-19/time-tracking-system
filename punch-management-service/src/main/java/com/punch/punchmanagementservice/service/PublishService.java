package com.punch.punchmanagementservice.service;

import com.punch.punchmanagementservice.entity.InputRequest;

public interface PublishService {

    void processRecord(InputRequest request);
}
