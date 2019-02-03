package com.heavenhr.recruitment.repository;

import com.heavenhr.recruitment.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by shridhar on 02/02/19.
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT app FROM Application app WHERE app.offer.id=?1")
    Optional<List<Application>> findByOffer(Long offerId);


    @Query("SELECT app FROM Application app WHERE app.offer.id = ?1 AND app.id = ?2")
    Optional<Application> findByOfferAndApplication(Long offerId, Long applicationId);
}