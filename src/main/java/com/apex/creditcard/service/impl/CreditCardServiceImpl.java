package com.apex.creditcard.service.impl;

import com.apex.creditcard.dao.CreditCardDao;
import com.apex.creditcard.model.CreditCard;
import com.apex.creditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardDao creditCardDao;

    @Override
    public List<CreditCard> getCreditCards() {
        return creditCardDao.findAll();
    }

    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardDao.save(creditCard);
    }
}
