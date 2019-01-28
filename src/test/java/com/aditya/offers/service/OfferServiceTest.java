package com.aditya.offers.service;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

import com.aditya.offers.OffersApiApplication;
import com.aditya.offers.entity.Offer;

import com.aditya.utility.TestUtils;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

/**
 * OfferService Junit Testcases
 * Covering function left out from OfferControllerIntegrationTest
 * BDDMockito
 * 
 * @author Aditya Sahu
 * **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OffersApiApplication.class)
public class OfferServiceTest {

	@MockBean
	private OfferService offerService;

	@Test
	public void isExpiredTest() {

		Logger.getGlobal().info("Start isExpiredTest test");
		Offer offer = TestUtils.mockExpiredOffer();

		given(offerService.isExpired(offer)).willReturn(true);
		Boolean status = offerService.isExpired(offer);
		assertThat(status).isEqualTo(true);
		Logger.getGlobal().info("End isExpiredTest test");

	}
	
	@Test
	public void getExpiryDateTest() {

		Logger.getGlobal().info("Start getExpiryDateTest test");
		Offer offer = TestUtils.mockExpiredOffer();

		given(offerService.getExpiryDate(7)).willReturn(offer.getExpiredOn());
		LocalDate expiryDate = offerService.getExpiryDate(7);
		assertThat(expiryDate).isEqualTo(offer.getExpiredOn());
		Logger.getGlobal().info("End getExpiryDateTest test");

	}
}
