package com.heavenhr.recruitment.service;

import com.google.common.collect.Lists;
import com.heavenhr.recruitment.dto.ApplicationInputDTO;
import com.heavenhr.recruitment.dto.CandidateDTO;
import com.heavenhr.recruitment.mapper.Mapper;
import com.heavenhr.recruitment.dto.ApplicationDTO;
import com.heavenhr.recruitment.entity.Application;
import com.heavenhr.recruitment.entity.ApplicationStatus;
import com.heavenhr.recruitment.entity.Offer;
import com.heavenhr.recruitment.exception.OfferNotFoundException;
import com.heavenhr.recruitment.repository.ApplicationRepository;
import com.heavenhr.recruitment.repository.OfferRepository;
import com.heavenhr.recruitment.service.application.impl.ApplicationServiceImpl;
import com.heavenhr.recruitment.service.offer.OfferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.heavenhr.recruitment.TestDataFactory.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shridhar on 03/02/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationServiceTest.class)
public class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private OfferRepository offerRepository;

    @Mock
    private OfferService offerService;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private ApplicationServiceImpl applicationService;


    @Test
    public void should_create_application_success() throws Exception {
        final Application application = getApplication(null, OFFER_ID);

        final Offer offer = getOffer(OFFER_ID, "software engineer");
        when(offerRepository.findById(OFFER_ID)).thenReturn(Optional.of(offer));

        when(applicationRepository.save(application)).thenReturn(application);

        final ApplicationInputDTO applicationInputDTO = new ApplicationInputDTO();
        applicationInputDTO.setCandidate(new CandidateDTO("shrid@gmail.com"));
        applicationInputDTO.setResumeText("application resume");
        applicationInputDTO.setStatus(ApplicationStatus.APPLIED);
        final ApplicationDTO applicationDTO = getApplicationDTO(APPLICATION_ID, OFFER_ID);

        when(mapper.toApplicationDTO(any())).thenReturn(applicationDTO);

        final ApplicationDTO result = applicationService.applyForOffer(OFFER_ID, applicationInputDTO);
        assertNotNull(result);
        assertThat(result.getOffer().getJobTitle(), is(application.getOffer().getJobTitle()));
        assertThat(result.getCandidate().getEmail(), is(application.getCandidate().getEmail()));
    }

    @Test
    public void should_throw_offer_not_found_exception() {
        final Application application = getApplication(null, (Offer) null);

        when(applicationRepository.save(application)).thenReturn(application);

        final ApplicationInputDTO applicationInputDTO = new ApplicationInputDTO();
        applicationInputDTO.setCandidate(new CandidateDTO("sd2@gmail.com"));
        applicationInputDTO.setResumeText("new resume");
        applicationInputDTO.setStatus(ApplicationStatus.APPLIED);

        ApplicationDTO result = null;
        try {
            result = applicationService.applyForOffer(OFFER_ID, applicationInputDTO);
        } catch (OfferNotFoundException e) {
            assertEquals("Offer not found with id : " + OFFER_ID, e.getMessage());
        }
        assertNull(result);
    }


    @Test
    public void should_return_application_by_id_success() {

        final Application application = getApplication(APPLICATION_ID, OFFER_ID);
        final ApplicationDTO applicationDTO = getApplicationDTO(APPLICATION_ID, OFFER_ID);
        when(applicationRepository.findById(APPLICATION_ID)).thenReturn(Optional.of(application));

        when(mapper.toApplicationDTO(any())).thenReturn(applicationDTO);
        final ApplicationDTO result = applicationService.findApplicationById(APPLICATION_ID);

        verify(applicationRepository, times(1)).findById(APPLICATION_ID);
        assertNotNull(result);
        assertThat(result.getId(), is(APPLICATION_ID));
    }

    @Test
    public void should_find_all_success() throws Exception {
        final Application application1 = getApplication(1L, OFFER_ID);
        final Application application2 = getApplication(2L, OFFER_ID);

        final ArrayList<Application> offerList = Lists.newArrayList(application1, application2);
        when(applicationRepository.findByOffer(OFFER_ID)).thenReturn(Optional.of(offerList));
        final ApplicationDTO applicationDTO = getApplicationDTO(1L, OFFER_ID);
        when(mapper.toApplicationDTO(any())).thenReturn(applicationDTO);

        final List<ApplicationDTO> applications = applicationService.findApplicationByOffer(OFFER_ID);
        assertThat(applications.size(), is(2));
        assertThat(applications.get(0).getId(), is(1L));
        assertThat(applications.get(0).getOffer().getId(), is(OFFER_ID));
    }

    @Test
    public void should_progress_status_success() throws Exception {
        final Application application = getApplication(APPLICATION_ID, OFFER_ID);
        when(applicationRepository.findById(APPLICATION_ID)).thenReturn(Optional.of(application));

        ArgumentCaptor<Application> applicationArgumentCaptor = ArgumentCaptor.forClass(Application.class);
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        applicationService.progressStatus(APPLICATION_ID, ApplicationStatus.HIRED);
        verify(applicationRepository, times(1)).save(applicationArgumentCaptor.capture());
        assertEquals(applicationArgumentCaptor.getValue().getStatus(), ApplicationStatus.HIRED);

    }
}