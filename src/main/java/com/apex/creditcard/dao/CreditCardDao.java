package com.apex.creditcard.dao;

import com.apex.creditcard.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {

    @Query(name = "from CreditCard where isDeleted is null")
    List<CreditCard> getCreditCards();
}
