package com.heavenhr.recruitment.service.notification.impl;

import com.heavenhr.recruitment.service.notification.NotificationService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;


/**
 * Created by shridhar on 02/02/19.
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void send(String message) {
        logger.info(message);
    }
}
