package com.aditya.offers.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.aditya.offers.entity.Offer;
import com.aditya.offers.entity.Offer.Status;

/**
 * 
 * Testing JPA layer, persisting Offer Entity using TestEntityManager to inbuilt database
 * 
 * By default, data JPA tests are transactional and roll back at the end of each test
 * 
 * @DataJpaTest tells to test JPA application layer
 * 
 * @author Aditya Sahu
 * 
 * **/

@RunWith(SpringRunner.class)
@DataJpaTest
public class OfferRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private OfferRepository offerRepository;

	@Test
	public void finfByOfferNameTest() throws Exception {
		
		Logger.getGlobal().info("Start isExpiredTest test");
		this.entityManager.persist(new Offer("Christmas", new BigDecimal(30.50), "Christmas Offer", Currency.getInstance("GBP"), Status.ACTIVE, 7, LocalDate.now(), LocalDate.now().plusDays(7)));
		List<Offer> offers = this.offerRepository.findByOfferName("Christmas");
		Offer offer = offers.get(0);
		assertThat(offer.getOfferName()).isEqualTo("Christmas");
		assertThat(offer.getActivationStatus()).isEqualTo(Status.ACTIVE);
		Logger.getGlobal().info("End isExpiredTest test");

	}
	

}
