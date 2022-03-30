package com.apex.creditcard.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CCI_CREDIT_CARD_INFO", schema = "public")
public class CreditCard {

    @Id
    @Column(name = "cci_pk")
    @SequenceGenerator(name = "SEQ", sequenceName = "sequencer", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    private Integer id;
    @Column(name = "credit_card_name")
    private String name;
    @Column(name = "credit_card_number")
    private String number;
    @Column(name = "credit_balance")
    private BigDecimal balance;
    @Column(name = "credit_limit")
    private BigDecimal limit;
    @Column(name = "created_date")
    private Date createdDate = new Date();

    private CreditCard() { }

    public static CreditCard buildCreditCard(CreditCardRequest request) {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(0);
        creditCard.setName(request.getCardName());
        creditCard.setNumber(request.getCardNumber());
        creditCard.setBalance(BigDecimal.ZERO);
        creditCard.setLimit(new BigDecimal(request.getLimit()));
        return creditCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "CreditCard <" +
                "creditCardName='" + name + '\'' +
                ", creditCardNumber='" + number + '\'' +
                ", limit=" + limit +
                ">";
    }
}
