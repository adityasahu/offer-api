package com.aditya.offers.service;

import java.time.LocalDate;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.offers.entity.Offer;
import com.aditya.offers.entity.Offer.Status;
import com.aditya.offers.exceptions.OfferIsExpiredException;
import com.aditya.offers.exceptions.OfferNotFoundException;
import com.aditya.offers.repository.OfferRepository;

/**
 * OfferService class for accessing Offer information
 * 
 * @author Aditya Sahu
 */

@Service
public class OfferService {

	protected Logger logger = Logger.getLogger(OfferService.class.getName());

	@Autowired
	OfferRepository offerRepository;

	/**
	 * Fetch all Offers .
	 * 
	 * @return Offers.
	 */
	public List<Offer> getAllOffers() {

		logger.info("offer-service getAllOffers() invoked: ");
		return (List<Offer>) offerRepository.findAll();

	}

	public Offer createNewOffer(Offer offer) {

		offer.setCreatedOn(LocalDate.now());
		offer.setExpiredOn(getExpiryDate(offer.getValidity()));
		return offerRepository.save(offer);
	}

	public List<Offer> findByDescription(String description) {

		logger.info("offer-service offerByDescription() invoked: " + description);
		return offerRepository.findByDescription(description);

	}

	public List<Offer> findByStatus(Status activationStatus) {

		logger.info("offer-service offersByName() invoked: " + activationStatus);
		return offerRepository.findByActivationStatus(activationStatus);

	}

	public List<Offer> findByName(String name) {

		logger.info("offer-service offersByName() invoked: " + name);
		return offerRepository.findByOfferName(name);
	}

	/**
	 * Merchant cancel Offer with the specified offer Id.
	 * 
	 * @param offerId A Long.
	 * @return Offers if cancelled.
	 * @throws OfferIsExpiredException If the offer is Expired .
	 */
	public Offer cancelOffer(Long offerId) {

		logger.info("offer-service cancelOffer() invoked: " + offerId);
		Offer offer = offerRepository.findByOfferId(offerId);
		if (isExpired(offer)) {
			throw new OfferIsExpiredException(offerId);
		}
		offer.setActivationStatus(Status.CANCELLED);
		logger.info("offer-service cancelOffer() cancelled offer: " + offerId);
		return offerRepository.save(offer);
	}

	public Boolean isExpired(Offer offer) {

		logger.info("offer-service isExpired() invoked: " + offer);
		if (offer.getActivationStatus() == Status.EXPIRED) {
			return true;
		}
		return false;
	}

	/**
	 * Fetch Expiry date with the specified validity.
	 * 
	 * @param validity A int.
	 * @return expiryDate A LocalDate.
	 */
	public LocalDate getExpiryDate(int validity) {

		logger.info("offer-service getExpiryDate() invoked: ");
		LocalDate today = LocalDate.now();
		LocalDate expiryDate = today.plusDays(validity);
		return expiryDate;
	}

	/**
	 * Schedule task runs every day @01:00Hrs Scheduler for expiring offers once its
	 * validity finishes.
	 * 
	 */
	public void expireOffers() {

		logger.info("offer-service expireOffers() invoked ");

		LocalDate today = LocalDate.now();
		List<Offer> offers = offerRepository.findByActivationStatusAndExpiredOn(Status.ACTIVE, today);
		offers.stream().forEach(o -> o.setActivationStatus(Status.EXPIRED));
		offerRepository.saveAll(offers);

		logger.info("offer-service expireOffers() finished ");

	}

}
