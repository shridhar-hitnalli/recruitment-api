package com.heavenhr.recruitment.service.application;

import com.heavenhr.recruitment.entity.ApplicationStatus;

/**
 * Created by shridhar on 02/02/19.
 */

public interface StatusChangeService {

    void after(Long applicationId, ApplicationStatus status);
}
