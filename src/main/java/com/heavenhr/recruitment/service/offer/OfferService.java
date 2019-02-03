package com.heavenhr.recruitment.service.offer;

import com.heavenhr.recruitment.dto.OfferDTO;
import com.heavenhr.recruitment.dto.OfferInputDTO;

import java.util.List;

/**
 * Created by shridhar on 02/02/19.
 */

public interface OfferService {

    List<OfferDTO> findAll();

    OfferDTO findById(Long offerId);

    OfferDTO create(OfferInputDTO offerInputDTO);

}
