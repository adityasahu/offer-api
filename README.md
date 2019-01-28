# offer-api

Framework - Spring Boot
Database - h2 database inbuild
Test  - JUnit, Mockito


Swagger API Document:
http://localhost:8080/swagger-ui.html
http://localhost:8080/v2/api-docs


Sample json payload:

{
  "activationStatus": "ACTIVE",
  "createdOn": "string",
  "currency": "string",
  "description": "string",
  "expiredOn": "string",
  "offerId": 0,
  "offerName": "string",
  "price": 0,
  "validity": 0
}

POST - Create new offer
http://localhost:8080/api/v1/offers/newOffer

GET - Get all offers
http://localhost:8080/api/v1/offers/all

PUT - Cancel Offer 
http://localhost:8080/api/v1/offers/{offerId}

GET - Get offers by Status
http://localhost:8080/api/v1/offers/status/{status}

GET - Get offers by offer name
http://localhost:8080/api/v1/offers/name/{name}

GET - Get offers  by description
http://localhost:8080/api/v1/offers/desc/{description}


