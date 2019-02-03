package com.heavenhr.recruitment.service.offer.impl;

import com.heavenhr.recruitment.dto.OfferInputDTO;
import com.heavenhr.recruitment.mapper.Mapper;
import com.heavenhr.recruitment.dto.OfferDTO;
import com.heavenhr.recruitment.entity.Offer;
import com.heavenhr.recruitment.exception.OfferAlreadyExistsException;
import com.heavenhr.recruitment.exception.OfferNotFoundException;
import com.heavenhr.recruitment.repository.OfferRepository;
import com.heavenhr.recruitment.service.offer.OfferService;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by shridhar on 02/02/19.
 */
@Service
public class OfferServiceImpl implements OfferService {

    private static final Logger logger = LoggerFactory.getLogger(OfferServiceImpl.class);

    private OfferRepository repository;

    private Mapper mapper;

    public OfferServiceImpl(OfferRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public List<OfferDTO> findAll() {

        logger.debug("findAll::");
        return repository.findAll()
                .stream()
                .map(offer -> mapper.toOfferDTO(offer))
                .collect(Collectors.toList());
    }

    @Override
    public OfferDTO findById(Long offerId) {

        logger.debug("findByOffer::{}", offerId);

        return mapper.toOfferDTO(repository.findById(offerId)
                        .orElseThrow(() -> new OfferNotFoundException("Offer not found with id : "+ offerId )));
    }

    @Override
    @Transactional
    public OfferDTO create(OfferInputDTO offerInputDTO) {

        logger.debug("create::");

        Offer offer = Offer.builder()
                .jobTitle(offerInputDTO.getJobTitle())
                .startDate(LocalDateTime.now())
                .numberOfApplications(0)
                .build();
        try {
            return mapper.toOfferDTO(repository.save(offer));
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            throw new OfferAlreadyExistsException("Offer already exists with job title : " + offer.getJobTitle());
        }


    }


}
