package com.aditya.offers.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.offers.entity.Offer;
import com.aditya.offers.entity.Offer.Status;
import com.aditya.offers.exceptions.OfferIsExpiredException;
import com.aditya.offers.exceptions.OfferNotFoundException;
import com.aditya.offers.service.OfferService;

/**
 * A RESTFul controller for accessing offer information.
 * 
 * @author Aditya Sahu
 */

@RestController
@RequestMapping
public class OfferController extends AbstractOfferController {
	
	protected Logger logger = Logger.getLogger(OfferController.class.getName());

	@Autowired
	OfferService offerService;

	/**
	 * Fetch all Offers .
	 * 
	 * @return Offers.
	 */

	@GetMapping(GET_ALL_URL)
	public List<Offer> getAllOffers() {

		logger.info("offer-controller getAllOffers() invoked: ");
		List<Offer> offers = offerService.getAllOffers();
		logger.info("offer-controller getAllOffers() found: " + offers);

		if (offers == null || offers.size() == 0)
			throw new OfferNotFoundException();
		else {
			return offers;
		}

	}

	/**
	 * Create new offer.
	 * 
	 * @param offer A Offer.
	 * @return Offer
	 */
	@PostMapping(NEW_OFFER_URL)
	public ResponseEntity<?> newOffer(@RequestBody @Valid Offer offer) {

		logger.info("offer-controller newOffer() invoked: " + offer);
		Offer offerCreated = offerService.createNewOffer(offer);
		logger.info("offer-controller newOffer() created: " + offerCreated);

		return ResponseEntity.status(HttpStatus.CREATED).body(offerCreated);

	}

	/**
	 * Fetch Offers with the specified description.
	 * 
	 * @param description A String.
	 * @return Offers if found.
	 * @throws OfferNotFoundException If the description is not recognised.
	 */
	@GetMapping(SEARCH_BY_DESC_URL)
	public List<Offer> offersByDescription(@PathVariable("description") String description) {

		logger.info("offer-controller offerByDescription() invoked: " + description);
		List<Offer> offers = offerService.findByDescription(description);
		logger.info("offer-controlelr offerByDescription() found: " + offers);

		if (offers == null || offers.size() == 0)
			throw new OfferNotFoundException(description);
		else {
			return offers;
		}

	}

	/**
	 * Fetch Offers with the specified name.
	 * 
	 * @param name A String.
	 * @return Offers if found.
	 * @throws OfferNotFoundException If the name is not recognised.
	 */
	@GetMapping(SEARCH_BY_NAME_URL)
	public List<Offer> offersByName(@PathVariable("name") String name) {

		logger.info("offer-controller offersByName() invoked: " + name);
		List<Offer> offers = offerService.findByName(name);
		logger.info("offer-controller offersByName() found: " + offers);

		if (offers == null || offers.size() == 0)
			throw new OfferNotFoundException(name);
		else {
			return offers;
		}

	}

	/**
	 * Fetch Offers with the specified status.
	 * 
	 * @param status A String.
	 * @return Offers if found.
	 * @throws OfferNotFoundException If the status is not recognised.
	 */
	@GetMapping(SEARCH_BY_STATUS_URL)
	public List<Offer> offersByStatus(@PathVariable("status") Status status) {

		logger.info("offer-controller offersByStatus() invoked: " + status);
		List<Offer> offers = offerService.findByStatus(status);
		logger.info("offer-controller offersByStatus() found: " + offers);

		if (offers == null || offers.size() == 0)
			throw new OfferNotFoundException(status);
		else {
			return offers;
		}

	}

	/**
	 * Merchant cancel Offer with the specified offer Id.
	 * 
	 * @param offerId A Long.
	 * @return Offers if cancelled.
	 * @throws OfferIsExpiredException If the offer is Expired .
	 */
	@PutMapping(CANCEL_OFFER_URL)
	Offer cancelOffer(@RequestBody Offer offer, @PathVariable Long offerId) {

		logger.info("offer-controller cancelOffer() invoked: ");
		return offerService.cancelOffer(offerId);
	}
}
