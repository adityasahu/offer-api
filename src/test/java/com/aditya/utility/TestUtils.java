package com.aditya.utility;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import com.aditya.offers.entity.Offer;
import com.aditya.offers.entity.Offer.Status;

public class TestUtils {
	
	public static Offer mockOffer() {
	
		Offer offer = new Offer("Christmas", new BigDecimal(30.50), "Christmas Offer", Currency.getInstance("GBP"), Status.ACTIVE, 7, LocalDate.now(), LocalDate.now().plusDays(7));	
		return offer;
	}
	
	public static Offer mockExpiredOffer() {
		
		Offer offer = new Offer("Christmas", new BigDecimal(30.50), "Christmas Offer", Currency.getInstance("GBP"), Status.EXPIRED, 7, LocalDate.now(), LocalDate.now().plusDays(7));	
		return offer;
	}


}
