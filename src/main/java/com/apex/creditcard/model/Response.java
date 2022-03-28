package com.apex.creditcard.model;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private List<CreditCard> creditCardList = new ArrayList<>();
    private String message;

    public List<CreditCard> getCreditCardList() {
        return creditCardList;
    }

    public void setCreditCardList(List<CreditCard> creditCardList) {
        this.creditCardList = creditCardList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
