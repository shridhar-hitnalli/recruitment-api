package com.heavenhr.recruitment.repository;

import com.heavenhr.recruitment.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shridhar on 02/02/19.
 */
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
