package com.aditya.offers.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.aditya.offers.OffersApiApplication;
import com.aditya.offers.entity.Offer;
import com.aditya.offers.entity.Offer.Status;
import com.aditya.offers.service.OfferService;
import com.aditya.utility.TestUtils;

import static org.mockito.ArgumentMatchers.any;

/**
 * The injected WebApplicationContext is a sub component of Springs main application context 
*and encapsulates Springs configuration for web related components such as the controller and exception handler we defined earlier.
*
*The @MockBean annotation tells Spring to create a mock instance of OfferService and add it to the application context so that it gets injected
*into the OfferController. We have a handle on it in the test so that we can define its behaviour prior to running each test.
*
*The setup method uses the statically imported webAppContextSetup method from MockMvcBuilders and the injected WebApplicationContext
*to build a MockMvc instance.
*
*@author Aditya Sahu

**/

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ OffersApiApplication.class })
public class OfferControllerIntegrationTest {

	private MockMvc mockMvc;

	
	@MockBean
	private OfferService offerService;
	
	@Autowired
    private WebApplicationContext webApplicationContext;

	
	@Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
	
	
	@Test
	public void should_GetAllOffers_When_ValidRequest() throws Exception{
		
		Offer offer = TestUtils.mockOffer();
		List<Offer> offers = Arrays.asList(offer);
		
		when(offerService.getAllOffers()).thenReturn(offers);
		
		mockMvc.perform(get("/api/v1/offers/all")
	               .contentType(MediaType.APPLICATION_JSON)
	               .content("{ \"offerName\": \"Christmas\", \"price\": 30.50}")
	               .accept(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	               .andExpect(jsonPath("$[0].offerName").value("Christmas"))
	               .andExpect(jsonPath("$[0].price").value(30.50))
	               .andExpect(jsonPath("$[0].description").value("Christmas Offer"));
					
		
		/*
		 * Uncomment this if you want to check response format -- Debugging/Testing only
		 * 
		 * MvcResult result = mockMvc.perform(get("/offers/all")
	               .contentType(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println("--------Response-------->"+content);*/
		
	}
	
	@Test
	public void should_CreateNewOffer_When_ValidRequest() throws Exception{
		
		Offer offer = TestUtils.mockOffer();
		
		when(offerService.createNewOffer(any(Offer.class))).thenReturn(offer);
		
		mockMvc.perform(post("/api/v1/offers/newOffer")
	               .contentType(MediaType.APPLICATION_JSON)
	               .content("{ \"offerName\": \"Christmas\", \"price\": 30.50}")
	               .accept(MediaType.APPLICATION_JSON))
	               .andExpect(status().isCreated())
	               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	               .andExpect(jsonPath("$.offerName").value("Christmas"))
	               .andExpect(jsonPath("$.price").value(30.50))
	               .andExpect(jsonPath("$.description").value("Christmas Offer"));
		
	}
	
	@Test
	public void should_GetOffersWithGivenOfferName_When_ValidRequest() throws Exception{
		
		Offer offer = TestUtils.mockOffer();
		List<Offer> offers = Arrays.asList(offer);
		
		when(offerService.findByName("Christmas")).thenReturn(offers);
		
		mockMvc.perform(get("/api/v1/offers/name/Christmas")
	               .contentType(MediaType.APPLICATION_JSON)
	               .content("{ \"offerName\": \"Christmas\", \"price\": 30.50}")
	               .accept(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	               .andExpect(jsonPath("$[0].offerName").value("Christmas"))
	               .andExpect(jsonPath("$[0].price").value(30.50))
	               .andExpect(jsonPath("$[0].description").value("Christmas Offer"));
		
	}
	
	@Test
	public void should_GetOffersWithGivenDescription_When_ValidRequest() throws Exception{
		
		Offer offer = TestUtils.mockOffer();
		List<Offer> offers = Arrays.asList(offer);
		
		when(offerService.findByDescription("Christmas Offer")).thenReturn(offers);
		
		mockMvc.perform(get("/api/v1/offers/desc/Christmas Offer")
	               .contentType(MediaType.APPLICATION_JSON)
	               .content("{ \"offerName\": \"Christmas\", \"price\": 30.50,\\\"description\\\": \\\"Christmas Offer\\\"}")
	               .accept(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	               .andExpect(jsonPath("$[0].offerName").value("Christmas"))
	               .andExpect(jsonPath("$[0].price").value(30.50))
	               .andExpect(jsonPath("$[0].description").value("Christmas Offer"));

		
	}
	
	@Test
	public void should_GetOffersWithGivenStatus_When_ValidRequest() throws Exception{
		
		Offer offer = TestUtils.mockOffer();
		List<Offer> offers = Arrays.asList(offer);
		
		when(offerService.findByStatus(Status.ACTIVE)).thenReturn(offers);
		
		mockMvc.perform(get("/api/v1/offers/status/ACTIVE")
	               .contentType(MediaType.APPLICATION_JSON)
	               .content("{ \"offerName\": \"Christmas\", \"price\": 30.50 ,\"description\": \"Christmas Offer\"}")
	               .accept(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	               .andExpect(jsonPath("$[0].offerName").value("Christmas"))
	               .andExpect(jsonPath("$[0].price").value(30.50))
	               .andExpect(jsonPath("$[0].description").value("Christmas Offer"));
		
	}
	
	@Test
	public void should_CancelOfferWithGivenOfferId_When_ValidRequest() throws Exception{
		
		Offer offer = TestUtils.mockOffer();
		offer.setOfferId(1L);
		
		when(offerService.cancelOffer(1L)).thenReturn(offer);
		
		mockMvc.perform(put("/api/v1/offers/1")
	               .contentType(MediaType.APPLICATION_JSON)
	               .content("{ \"offerName\": \"Christmas\", \"price\": 30.50 ,\"description\": \"Christmas Offer\"}")
	               .accept(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	               .andExpect(jsonPath("$.offerName").value("Christmas"))
	               .andExpect(jsonPath("$.price").value(30.50))
	               .andExpect(jsonPath("$.description").value("Christmas Offer"));
		
	}
	
	@Test
	public void should_Return404_When_OfferNotFound() throws Exception {
	  
		
		when(offerService.findByName("Christmas")).thenReturn(null);
		
		mockMvc.perform(get("/offers/name/Christmas")
	               .accept(MediaType.APPLICATION_JSON))
	               .andExpect(status().isNotFound());
	}
}
