package com.heavenhr.recruitment.mapper;

import com.heavenhr.recruitment.dto.ApplicationDTO;
import com.heavenhr.recruitment.dto.CandidateDTO;
import com.heavenhr.recruitment.dto.OfferDTO;
import com.heavenhr.recruitment.entity.Application;
import com.heavenhr.recruitment.entity.Candidate;
import com.heavenhr.recruitment.entity.Offer;
import org.springframework.stereotype.Component;


/**
 * Created by shridhar on 02/02/19.
 */
@Component
public class Mapper {

    public ApplicationDTO toApplicationDTO(Application application) {
        return ApplicationDTO.builder()
                .id(application.getId())
                .resumeText(application.getResumeText())
                .status(application.getStatus())
                .candidate(CandidateDTO.builder().email(application.getCandidate().getEmail()).build())
                .offer(toOfferDTO(application.getOffer()))
                .build();
    }
    public Application toApplication(ApplicationDTO application) {
        return Application.builder()
                .resumeText(application.getResumeText())
                .status(application.getStatus())
                .candidate(Candidate.builder().email(application.getCandidate().getEmail()).build())
                .offer(toOffer(application.getOffer()))
                .build();
    }

    public OfferDTO toOfferDTO(Offer offer) {
        return OfferDTO.builder()
                .id(offer.getId())
                .jobTitle(offer.getJobTitle())
                .numberOfApplications(offer.getNumberOfApplications())
                .startDate(offer.getStartDate())
                .build();
    }


    private Offer toOffer(OfferDTO offer) {
        if (offer == null) {
            return null;
        }
        return Offer.builder()
                .jobTitle(offer.getJobTitle())
                .numberOfApplications(offer.getNumberOfApplications())
                .startDate(offer.getStartDate())
                .build();
    }

}

