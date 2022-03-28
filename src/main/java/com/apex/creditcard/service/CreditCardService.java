package com.apex.creditcard.service;

import com.apex.creditcard.model.CreditCard;
import com.apex.creditcard.model.CreditCardRequest;

import java.util.List;

public interface CreditCardService {

    List<CreditCard> getCreditCards();
    CreditCard saveCreditCard(CreditCard creditCard);
    CreditCard processCreditCard(CreditCardRequest request);
}
