package com.apex.creditcard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "CCI_CREDIT_CARD_INFO")
public class CreditCard {

    @Id
    @Column(name = "cci_pk")
    private Integer creditCardPk;
    @Column(name = "credit_card_name")
    private String creditCardName;
    @Transient
    private String shortenedCreditCardNumber;
    @Column(name = "credit_card_number")
    private String creditCardNumber;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "limit")
    private BigDecimal limit;
    @Column(name = "is_deleted")
    private Date isDeleted = null;
    @Column(name = "created_date")
    private Date createdDate = new Date();

    private String getShortenedCreditCardNumber() {
        return shortenedCreditCardNumber;
    }

    private void setShortenedCreditCardNumber(String creditCardNumber) {
        // TODO - some logic to mask credit card number
        this.shortenedCreditCardNumber = creditCardNumber;
    }

    public Integer getCreditCardPk() {
        return creditCardPk;
    }

    public void setCreditCardPk(Integer creditCardPk) {
        this.creditCardPk = creditCardPk;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
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

    public Date getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Date isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardName='" + creditCardName + '\'' +
                ", creditCardNumber='" + shortenedCreditCardNumber + '\'' +
                ", limit=" + limit +
                '}';
    }
}
