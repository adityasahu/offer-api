package com.aditya.offers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Allow the controller to return a 406 if an offer can not be cancelled by simply
 * throwing this exception. The @ResponseStatus causes Spring MVC to return a
 * 406 instead of the usual 500.
 * 
 * @author Aditya Sahu
 */

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class OfferIsExpiredException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OfferIsExpiredException(Long id) {
		super("Offer cannot be cancelled as it is already expired!! offer Id"+id);
	}

}
