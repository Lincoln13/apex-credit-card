package com.apex.creditcard.model;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private List<CreditCard> creditCards = new ArrayList<>();
    private String message;

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCardList) {
        this.creditCards = creditCardList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
