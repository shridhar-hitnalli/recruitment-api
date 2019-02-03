package com.heavenhr.recruitment.service.application;

import com.heavenhr.recruitment.dto.ApplicationDTO;
import com.heavenhr.recruitment.dto.ApplicationInputDTO;
import com.heavenhr.recruitment.entity.ApplicationStatus;

import java.util.List;

/**
 * Created by shridhar on 02/02/19.
 */

public interface ApplicationService {

    List<ApplicationDTO> findApplicationByOffer(Long offerId);

    List<ApplicationDTO> findAll();

    ApplicationDTO findByOfferAndApplication(Long offerId, Long applicationId);

    ApplicationDTO findApplicationById(Long applicationId);

    void progressStatus(Long id, ApplicationStatus status);

    ApplicationDTO applyForOffer(Long offerId, ApplicationInputDTO application);

    Long findApplications();
}
