package com.heavenhr.recruitment.service.application.impl;

import com.heavenhr.recruitment.entity.Application;
import com.heavenhr.recruitment.entity.ApplicationStatus;
import com.heavenhr.recruitment.exception.ApplicationNotFoundException;
import com.heavenhr.recruitment.repository.ApplicationRepository;
import com.heavenhr.recruitment.service.application.StatusChangeService;
import com.heavenhr.recruitment.service.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by shridhar on 02/02/19.
 */
@Service
public class StatusChangeServiceImpl implements StatusChangeService {

    private static final Logger logger = LoggerFactory.getLogger(StatusChangeServiceImpl.class);

    private final NotificationService notificationService;

    private final ApplicationRepository applicationRepository;

    public StatusChangeServiceImpl(NotificationService notificationService, ApplicationRepository applicationRepository) {
        this.notificationService = notificationService;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public void after(Long applicationId, ApplicationStatus applicationStatus) {

        logger.debug("after: applicationId : {} ", applicationId);

        Application application = applicationRepository.findById(applicationId)
                                    .orElseThrow(() -> new ApplicationNotFoundException("Application not found with id : " + applicationId + ". Can not send notification."));

        String message = null;

        final String email = application.getCandidate().getEmail();

        switch (applicationStatus) {
            case APPLIED:
                message = "Applicant " + email + " has applied.";
                break;
            case INVITED:
                message = "Applicant " + email + " has been invited.";
                break;
            case REJECTED:
                message = "Applicant " + email + " has been rejected.";
                break;
            case HIRED:
                message = "Applicant " + email + " has been hired";
                break;
        }

        logger.debug("after: send notification : {} ", message);
        notificationService.send(message);
    }


}
