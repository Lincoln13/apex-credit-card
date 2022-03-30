package com.apex.creditcard.service.impl;

import com.apex.creditcard.dao.CreditCardDao;
import com.apex.creditcard.model.CreditCard;
import com.apex.creditcard.model.CreditCardRequest;
import com.apex.creditcard.service.CreditCardService;
import com.apex.creditcard.validator.CreditCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.apex.creditcard.exception.BusinessException;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardDao creditCardDao;

    @Value("${business.cardExists}")
    private String creditCardExists;
    @Value("${business.invalidCreditLimit}")
    private String invalidCreditLimit;
    @Value("${business.invalidCreditCardNumber}")
    private String invalidCreditCardNumber;

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

        CreditCardValidator creditCardValidator = new CreditCardValidator(creditCardDao);

        if(!creditCardValidator.areCreditLimitAllNumeric(request.getLimit())) {
            throw new BusinessException(invalidCreditLimit);
        }

        if (!creditCardValidator.isValidCreditCardNumber(request.getCardNumber())) {
            throw new BusinessException(invalidCreditCardNumber);
        }
        if (creditCardValidator.doesCardNumberAlreadyExists(request.getCardNumber())) {
            throw new BusinessException(creditCardExists);
        }
        return CreditCard.buildCreditCard(request);
    }
}