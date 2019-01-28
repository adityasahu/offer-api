package com.aditya.offers.controller;

import java.util.Map;


/**
 * Abstract Controller to handle generic operations for Controllers.
 * 
 * As of now not implemented headers but for future reference
 *
 * @param <K>
 * @param <V>
 * @author Aditya Sahu
 */
public abstract class AbstractOfferController<K extends String, V extends String> {

	//public static final String API_URL = "api/v1/offers";
    public static final String GET_ALL_URL = "api/v1/offers/all";
    public static final String NEW_OFFER_URL = "api/v1/offers/newOffer";
    public static final String SEARCH_BY_DESC_URL = "api/v1/offers/desc/{description}";
    public static final String SEARCH_BY_NAME_URL = "api/v1/offers/name/{name}";
    public static final String SEARCH_BY_STATUS_URL = "api/v1/offers/status/{status}";
    public static final String CANCEL_OFFER_URL = "api/v1/offers/{offerId}";
    public static final String SWAGGER_DOCS_UI_URL = "/swagger-ui.html";
    public static final String SWAGGER_DOCS_API_URL = "/v2/api-docs";

    protected V getHeaderValue(Map<K, V> headers, K headerName) {
        // todo 
        return null;
    }
}
