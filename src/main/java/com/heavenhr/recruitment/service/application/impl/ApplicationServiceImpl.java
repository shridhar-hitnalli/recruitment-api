package com.heavenhr.recruitment.service.application.impl;

import com.heavenhr.recruitment.dto.ApplicationDTO;
import com.heavenhr.recruitment.dto.ApplicationInputDTO;
import com.heavenhr.recruitment.entity.Application;
import com.heavenhr.recruitment.entity.ApplicationStatus;
import com.heavenhr.recruitment.entity.Candidate;
import com.heavenhr.recruitment.entity.Offer;
import com.heavenhr.recruitment.exception.ApplicationDataConflictException;
import com.heavenhr.recruitment.exception.ApplicationNotFoundException;
import com.heavenhr.recruitment.exception.OfferNotFoundException;
import com.heavenhr.recruitment.mapper.Mapper;
import com.heavenhr.recruitment.repository.ApplicationRepository;
import com.heavenhr.recruitment.repository.OfferRepository;
import com.heavenhr.recruitment.service.application.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by shridhar on 02/02/19.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private ApplicationRepository applicationRepository;

    private OfferRepository offerRepository;

    private Mapper mapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, OfferRepository offerRepository, Mapper mapper) {
        this.applicationRepository = applicationRepository;
        this.offerRepository = offerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ApplicationDTO> findApplicationByOffer(Long offerId) {
        logger.debug("findApplicationByOffer{} :", offerId);

        List<Application> applications = applicationRepository.findByOffer(offerId)
                                                    .orElseThrow(() -> new ApplicationNotFoundException("Application not found with offer id : " + offerId));
        return applications
                .stream()
                .map(app -> mapper.toApplicationDTO(app))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDTO> findAll() {

        logger.debug("findAll::");

        List<Application> applications = applicationRepository.findAll();

        return applications
                .stream()
                .map(app -> mapper.toApplicationDTO(app))
                .collect(Collectors.toList());
    }


    @Override
    public ApplicationDTO findByOfferAndApplication(Long offerId, Long applicationId) {

        logger.debug("findByOfferAndApplication {} {}", offerId, applicationId);

        Application application = applicationRepository.findByOfferAndApplication(offerId, applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException(" Application not found with Offer id : " + offerId + " and Application id : " +applicationId));

        return mapper.toApplicationDTO(application);
    }

    @Override
    public Long findApplications() {
        logger.debug("findApplications::");

        return applicationRepository.count();
    }

    @Override
    public ApplicationDTO findApplicationById(Long id) {

        Application application = applicationRepository.findById(id)
                                        .orElseThrow(() -> new ApplicationNotFoundException("Application not found with id : " +id));

        return mapper.toApplicationDTO(application);
    }


    @Override
    @Transactional
    public void progressStatus(Long id, ApplicationStatus status) {
        logger.debug("progressStatus:: {}, {}", id, status);

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found with id : " + id));

        ApplicationStatus oldStatus = application.getStatus();

        if(!oldStatus.equals(status)) {
            logger.debug("status changed from {} to {}", oldStatus, status);

            application.setStatus(status);
            applicationRepository.save(application);
        }
    }

    @Override
    @Transactional
    public ApplicationDTO applyForOffer(Long offerId, ApplicationInputDTO applicationInDTO) {

        logger.debug("applyForOffer:: {}", offerId);

        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new OfferNotFoundException("Offer not found with id : " + offerId));

        validateEmailForOffer(applicationInDTO.getCandidate().getEmail(), offer);

        offer.setNumberOfApplications(offer.getNumberOfApplications() + 1);
        Application application = Application.builder()
                                        .candidate(new Candidate(applicationInDTO.getCandidate().getEmail()))
                                        .offer(offer)
                                        .status(ApplicationStatus.APPLIED)
                                        .resumeText(applicationInDTO.getResumeText())
                                        .build();

        return mapper.toApplicationDTO(applicationRepository.save(application));
    }

    private void validateEmailForOffer(String email, Offer offer) {
        for (Application applicationEntity : offer.getApplications()) {
            if (email.equals(applicationEntity.getCandidate().getEmail())) {
                throw new ApplicationDataConflictException("Candidate already registered for another offer");
            }
        }
    }


}
