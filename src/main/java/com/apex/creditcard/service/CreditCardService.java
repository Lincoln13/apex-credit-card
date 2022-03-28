package com.apex.creditcard.service;

import com.apex.creditcard.model.CreditCard;

import java.util.List;

public interface CreditCardService {

    List<CreditCard> getCreditCards();
    CreditCard saveCreditCard(CreditCard creditCard);
}
