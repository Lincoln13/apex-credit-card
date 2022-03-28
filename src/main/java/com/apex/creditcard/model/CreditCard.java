package com.apex.creditcard.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CCI_CREDIT_CARD_INFO", schema = "public")
public class CreditCard {

    @Id
    @Column(name = "cci_pk")
    @SequenceGenerator(name = "SEQ", sequenceName = "sequencer", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    private Integer creditCardPk;
    @Column(name = "credit_card_name")
    private String creditCardName;
    @Column(name = "credit_card_number")
    private String creditCardNumber;
    @Column(name = "credit_balance")
    private BigDecimal balance;
    @Column(name = "credit_limit")
    private BigDecimal limit;
    @Column(name = "is_deleted")
    private Date isDeleted = null;
    @Column(name = "created_date")
    private Date createdDate = new Date();

    private CreditCard() { }

    public static CreditCard buildCreditCard(CreditCardRequest request) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardPk(0);
        creditCard.setCreditCardName(request.getCardName());
        creditCard.setCreditCardNumber(request.getCardNumber());
        creditCard.setBalance(BigDecimal.ZERO);
        creditCard.setLimit(request.getLimit());
        return creditCard;
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
        return "CreditCard <" +
                "creditCardName='" + creditCardName + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", limit=" + limit +
                ">";
    }
}
