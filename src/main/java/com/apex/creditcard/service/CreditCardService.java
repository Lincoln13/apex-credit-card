package com.apex.creditcard.service;

import com.apex.creditcard.model.CreditCard;
import com.apex.creditcard.model.CreditCardRequest;

import java.util.List;

public interface CreditCardService {

    /**
     * Method makes database call and retrieves all credit cards
     * from the database. Returns a list of credit cards to the
     * caller.
     *
     * @return list of credit cards
     */
    List<CreditCard> getCreditCards();

    /**
     * Method saves new credit card into the database. Before
     * saving, it makes sure all data are in proper format.
     * Accepts Credit card entity as parameter.
     *
     * @param creditCard credit card entity
     * @return credit card saved to the DB
     */
    CreditCard saveCreditCard(CreditCard creditCard);

    /**
     * Method takes in the unfiltered request received from the UI
     * and validates if all the important data are in proper format
     * or shape. If yes only then it proceeds to build a credit card
     * entity for persistence. Else throws runtime business exceptions
     * with suitable messages.
     *
     * @param request incoming client request
     * @return formatted credit card info
     */
    CreditCard processCreditCard(CreditCardRequest request);
}
