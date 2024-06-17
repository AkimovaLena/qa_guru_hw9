package model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Offer {

    public String getId() {
        return id;
    }

    public int getOfferNumber() {
        return offerNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public State getState() {
        return state;
    }

    public String getIncoterms() {
        return incoterms;
    }

    public String getPlaceOfShipment() {
        return placeOfShipment;
    }

    public Boolean getProductInStock() {
        return productInStock;
    }

    public String getComment() {
        return comment;
    }

    public BusinessUnits[] getBus() {
        return bus;
    }

    String id;
    int offerNumber;
    String createdAt;
    State state;
    String incoterms;
    String placeOfShipment;
    Boolean productInStock;
    String comment;
    @JsonSetter("businessUnits")
    BusinessUnits[] bus;


}
