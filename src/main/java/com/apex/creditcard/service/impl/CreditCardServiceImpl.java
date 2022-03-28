package com.apex.creditcard.service.impl;

import com.apex.creditcard.dao.CreditCardDao;
import com.apex.creditcard.model.CreditCard;
import com.apex.creditcard.model.CreditCardRequest;
import com.apex.creditcard.service.CreditCardService;
import com.apex.creditcard.validator.CreditCardNumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apex.creditcard.exception.BusinessException;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardDao creditCardDao;
    @Autowired
    private CreditCardNumberValidator creditCardNumberValidator;

    @Override
    public List<CreditCard> getCreditCards() {
        return creditCardDao.findAll();
    }

    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardDao.save(creditCard);
    }

    @Override
    public CreditCard processCreditCard(CreditCardRequest request) throws BusinessException {
        Boolean isValidCreditCardNumber = creditCardNumberValidator.isValidCreditCardNumber(request.getCardNumber());
        if (!isValidCreditCardNumber) {
            throw new BusinessException("Invalid credit card number!");
        }
        Boolean isCardAlreadyAdded = creditCardNumberValidator.doesCardNumberAlreadyExists(request.getCardNumber());
        if (isCardAlreadyAdded) {
            throw new BusinessException("Card already added!");
        }
        return CreditCard.buildCreditCard(request);
    }
}