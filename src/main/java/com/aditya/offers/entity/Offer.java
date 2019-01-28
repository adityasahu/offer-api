package com.aditya.offers.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistent offer entity with JPA markup. Offers are stored in an H2
 * relational database.
 * 
 * @author Aditya Sahu
 */

@Entity
@Table(name = "OFFER")
public class Offer {
	
	public enum Status {
        ACTIVE, EXPIRED, CANCELLED
    }

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long offerId;
	private String offerName;
	private BigDecimal price;
	private String description;
	private Currency currency;
	private Status activationStatus;
	private int validity;
	private LocalDate createdOn;
	private LocalDate expiredOn;
	
	public Offer() {
		
	}
	
	public Offer(String offerName, BigDecimal price, String description, Currency currency, Status activationStatus,
			int validity, LocalDate createdOn, LocalDate expiredOn) {
		super();
		this.offerName = offerName;
		this.price = price;
		this.description = description;
		this.currency = currency;
		this.activationStatus = activationStatus;
		this.validity = validity;
		this.createdOn = createdOn;
		this.expiredOn = expiredOn;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Status getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(Status activationStatus) {
		this.activationStatus = activationStatus;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDate getExpiredOn() {
		return expiredOn;
	}

	public void setExpiredOn(LocalDate expiredOn) {
		this.expiredOn = expiredOn;
	}
	

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

	@Override
	public String toString() {
		return "Offer [offerName=" + offerName + ", price=" + price + ", description=" + description + ", currency="
				+ currency + ", activationStatus=" + activationStatus + ", validity=" + validity + ", createdOn="
				+ createdOn + ", expiredOn=" + expiredOn + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activationStatus == null) ? 0 : activationStatus.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expiredOn == null) ? 0 : expiredOn.hashCode());
		result = prime * result + ((offerName == null) ? 0 : offerName.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + validity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (activationStatus != other.activationStatus)
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expiredOn == null) {
			if (other.expiredOn != null)
				return false;
		} else if (!expiredOn.equals(other.expiredOn))
			return false;
		if (offerName == null) {
			if (other.offerName != null)
				return false;
		} else if (!offerName.equals(other.offerName))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (validity != other.validity)
			return false;
		return true;
	}

}
