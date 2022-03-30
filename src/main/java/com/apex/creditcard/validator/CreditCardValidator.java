package com.apex.creditcard.validator;

import com.apex.creditcard.dao.CreditCardDao;
import com.apex.creditcard.model.CreditCard;
import org.springframework.stereotype.Component;

@Component
public class CreditCardValidator {

    private CreditCardDao dao;

    public CreditCardValidator() {}

    public CreditCardValidator(CreditCardDao dao) {
        this.dao = dao;
    }

    public Boolean validateLimitValue(String limit) {
        return areAllNumbers(limit);
    }

    public Boolean doesCardNumberAlreadyExists(String creditCardNumber) {
        CreditCard creditCardWithUs;
        creditCardWithUs = dao.findByNumber(creditCardNumber);
        return (creditCardWithUs != null);
    }

    public Boolean isValidCreditCardNumber(String creditCardNumber) {

        creditCardNumber = creditCardNumber.replace("-", "");

        return isCreditCardNumbersBounded(creditCardNumber) &&
                areAllNumbers(creditCardNumber) &&
                luhnAlgorithmValidator(creditCardNumber);
    }

    private Boolean isCreditCardNumbersBounded(String creditCardNumber) {

        int numberOfDigits = creditCardNumber.length();
        return (numberOfDigits > 0 && numberOfDigits <= 16);
    }

    private Boolean areAllNumbers(String creditCardNumber) {
        for (int i = creditCardNumber.length() - 1; i >= 0; i--) {
            if (creditCardNumber.charAt(i) < 48 || creditCardNumber.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }

    private Boolean luhnAlgorithmValidator(String creditCardNumber) {

        int numberOfDigits = creditCardNumber.length();
        boolean isSecondDigit = false;
        int total = 0;
        for (int i = numberOfDigits - 1; i >= 0; i--) {
            // ASCII of the character - ASCII of the value
            // substitute for parseInt. gives the number at 0 (48)
            int d = creditCardNumber.charAt(i) - '0';
            d = (isSecondDigit) ? d*2 : d;

            // how it works?
            // say d = 12; d/10=1 and d % 10 = 2 and
            // we adding (1+2) to the total.
            // Now say d = 8; d/10 = 0 and d%10 = 8 and
            // we are adding (0+8) to the total.

            // adding quotient to the total
            total += d / 10;
            // adding remainder to the total
            total += d % 10;

            isSecondDigit = !isSecondDigit;
        }
        return (total % 10 == 0);
    }
}
