package com.apex.creditcard.dao;

import com.apex.creditcard.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {
    CreditCard findByNumber(String number);
}
