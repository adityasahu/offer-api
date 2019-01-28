package com.aditya.offers.repository;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aditya.offers.entity.Offer;
import com.aditya.offers.entity.Offer.Status;

/**
 * Repository for Offer data implemented using Spring Data JPA.
 * 
 * @author Aditya Sahu
 */

@Repository
public interface OfferRepository extends CrudRepository<Offer,Long> {

	/**
	 * Find offers with the specified description.
	 *
	 * @param description
	 *            Any alphabetic string.
	 * @return The list of matching offers - always non-null, but may be
	 *         empty.
	 */
	List<Offer> findByDescription(String description); 
	
	/**
	 * Find offers with the specified activationStatus.
	 *
	 * @param activationStatus
	 * @return The list of matching offers - always non-null, but may be
	 *         empty.
	 */
	List<Offer> findByActivationStatus(Status activationStatus);
	
	/**
	 * Find offers with the specified name.
	 *
	 * @param name	 
	 *            Any alphabetic string.
	 * @return The list of matching offers - always non-null, but may be
	 *         empty.
	 */
	List<Offer> findByOfferName(String name);
	
	/**
	 * Find offer with the specified ID.
	 *
	 * @param id
	 * @return The offer if found, null otherwise.
	 */
	Offer findByOfferId(Long id);
	
	/**
	 * Find offers with the specified name.
	 *
	 * @param status, todays date
	 * @return The list of matching offers - always non-null, but may be
	 *         empty.
	 */
	List<Offer> findByActivationStatusAndExpiredOn(Status status, LocalDate date);

}
