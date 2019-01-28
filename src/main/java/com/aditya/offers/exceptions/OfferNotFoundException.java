package com.aditya.offers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aditya.offers.entity.Offer.Status;

/**
 * Allow the controller to return a 404 if an offer is not found by simply
 * throwing this exception. The @ResponseStatus causes Spring MVC to return a
 * 404 instead of the usual 500.
 * 
 * @author Aditya Sahu
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OfferNotFoundException(String description) {
		super("No such offer for description: " + description);
	}

	public OfferNotFoundException(Status status) {
		super("No offer for status: " + status);
	}

	public OfferNotFoundException(Long offerId) {
		super("No offer for offerId: " + offerId);
	}

	public OfferNotFoundException() {
		super("No offer exists");
	}
	
}

