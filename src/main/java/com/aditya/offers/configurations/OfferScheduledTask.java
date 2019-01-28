package com.aditya.offers.configurations;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aditya.offers.service.OfferService;

/**
 * A Scheduler for checking validity of offer it is cron for 01:00 everyday
 * 
 * @author Aditya Sahu
 */

@Component
public class OfferScheduledTask {
	
	private static final Logger log = LoggerFactory.getLogger(OfferScheduledTask.class);
	
	@Autowired
	OfferService offerService;
	
    @Scheduled(cron="0 0 1 * * *")
    public void validityCheck() {
    	
        log.info("Scheduler called for expiring offers if its validity is finishes");
        offerService.expireOffers();
    }

}
