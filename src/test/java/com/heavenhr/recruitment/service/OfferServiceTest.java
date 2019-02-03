package com.heavenhr.recruitment.service;

import com.google.common.collect.Lists;
import com.heavenhr.recruitment.dto.OfferInputDTO;
import com.heavenhr.recruitment.mapper.Mapper;
import com.heavenhr.recruitment.dto.OfferDTO;
import com.heavenhr.recruitment.entity.Offer;
import com.heavenhr.recruitment.exception.OfferAlreadyExistsException;
import com.heavenhr.recruitment.repository.OfferRepository;
import com.heavenhr.recruitment.service.offer.impl.OfferServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@SpringBootTest(classes = OfferServiceTest.class)
public class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private OfferServiceImpl offerService;

    @Test
    public void should_create_offer_success() {

        // given
        final String jobTitle = "software engineer";
        Offer offer = getOffer(null, jobTitle);
        OfferDTO offerDTO = getOfferDTO(null, jobTitle);

        when(offerRepository.save(any(Offer.class))).thenReturn(offer);

        when(mapper.toOfferDTO(any(Offer.class))).thenReturn(offerDTO);

        assertNotNull(offerService.create(new OfferInputDTO(jobTitle)));

    }

    @Test
    public void should_throw_offer_already_exists_exception() {

        final String jobTitle = "backend engineer";
        Offer offer = mock(Offer.class);
        when(offerRepository.save(offer)).thenThrow(OfferAlreadyExistsException.class);

        try {
            offerService.create(new OfferInputDTO(jobTitle));
        } catch (OfferAlreadyExistsException e) {
            assertEquals("Offer already exists with job title : " + offer.getJobTitle(), e.getMessage());
        }

        //then
        verify(offerRepository, never()).save(offer);

    }

    @Test
    public void should_return_offer_id_success() {

        final Offer offer = getOffer(OFFER_ID, "qa engineer");
        when(offerRepository.findById(OFFER_ID)).thenReturn(Optional.of(offer));
        when(mapper.toOfferDTO(offer)).thenReturn(getOfferDTO(OFFER_ID, "qa engineer"));

        final OfferDTO offerDTO1 = offerService.findById(OFFER_ID);

        verify(offerRepository, times(1)).findById(OFFER_ID);
        assertNotNull(offerDTO1);
        assertThat(offerDTO1.getId(), is(OFFER_ID));
        assertEquals(offerDTO1.getJobTitle(), "qa engineer");
    }

    @Test
    public void should_find_all_success() throws Exception {
        final Offer offer1 = getOffer(OFFER_ID, "title");
        final Offer offer2 = getOffer(OFFER_ID, "title software");
        final ArrayList<Offer> offerList = Lists.newArrayList(offer1, offer2);
        when(offerRepository.findAll()).thenReturn(offerList);
        when(mapper.toOfferDTO(offer1)).thenReturn(getOfferDTO(OFFER_ID, "title"));
        when(mapper.toOfferDTO(offer2)).thenReturn(getOfferDTO(OFFER_ID, "title software"));

        final List<OfferDTO> offers = offerService.findAll();
        assertThat(offers.size(), is(2));
        assertThat(offers.get(0).getId(), is(1L));
        assertThat(offers.get(0).getJobTitle(), is("title"));
        assertThat(offers.get(1).getJobTitle(), is("title software"));
    }

}