package com.heavenhr.recruitment;

import com.heavenhr.recruitment.dto.ApplicationDTO;
import com.heavenhr.recruitment.dto.CandidateDTO;
import com.heavenhr.recruitment.dto.OfferDTO;
import com.heavenhr.recruitment.entity.Application;
import com.heavenhr.recruitment.entity.ApplicationStatus;
import com.heavenhr.recruitment.entity.Candidate;
import com.heavenhr.recruitment.entity.Offer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestDataFactory {

    public static final Long OFFER_ID = 1L;
    public static final Long APPLICATION_ID = 2L;

    public static Offer getOffer(final Long id, final String jobTitle) {
        return Offer.builder()
                .id(id)
                .jobTitle(jobTitle)
                .numberOfApplications(0)
                .startDate(LocalDateTime.now())
                .applications(new ArrayList<>())
                .build();
    }

    public static OfferDTO getOfferDTO(final Long id, final String jobTitle) {
        return OfferDTO.builder()
                .id(id)
                .jobTitle(jobTitle)
                .numberOfApplications(0)
                .startDate(LocalDateTime.now())
                .build();
    }

    public static Application getApplication(final Long id, final Long offerId) {
        return getApplication(id, getOffer(offerId, "software engineer"));
    }

    public static Application getApplication(final Long id, final Offer offer) {
        return Application.builder()
                .id(id)
                .resumeText("application resume")
                .candidate(Candidate.builder()
                .email("shrid@gmail.com").build())
                .status(ApplicationStatus.APPLIED)
                .offer(offer)
                .build();
    }

    public static ApplicationDTO getApplicationDTO(final Long id, final Long offerId) {
        return ApplicationDTO.builder()
                             .id(id)
                             .resumeText("application resume")
                             .candidate(CandidateDTO.builder().email("shrid@gmail.com").build())
                             .status(ApplicationStatus.APPLIED)
                             .offer(getOfferDTO(id, "software engineer"))
                             .build();
    }

    public static String createURLWithPort(String uri, int port) {
        return "http://localhost:" + port + uri;
    }
}
