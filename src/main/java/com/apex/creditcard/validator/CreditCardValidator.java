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

    /**
     * Validates credit card limit if it contains all numbers.
     *
     * @param limit credit card limit provided by the users.
     * @return true if all are numbers else false.
     */
    public Boolean areCreditLimitAllNumeric(String limit) {
        return areAllNumbers(limit);
    }

    /**
     * Checks our database and looks if the credit card number
     * already exists. If yes then system doesn't let to store
     * credit card information into the database.
     *
     * @param creditCardNumber credit card number provided by the users
     * @return true if card already exists else false.
     */
    public Boolean doesCardNumberAlreadyExists(String creditCardNumber) {
        CreditCard creditCardWithUs;
        creditCardWithUs = dao.findByNumber(creditCardNumber);
        return (creditCardWithUs != null);
    }

    /**
     * Takes in a credit card number and performs various operation to
     * check its validity like if all characters are numeric,
     * upto nineteen characters can be entered and if it obeys Luhn 10
     * algorithm.
     *
     * @param creditCardNumber credit card number provided by the user
     * @return true if all conditions are valid else false
     */
    public Boolean isValidCreditCardNumber(String creditCardNumber) {

        creditCardNumber = creditCardNumber.replace("-", "");

        return isCreditCardNumbersBounded(creditCardNumber) &&
                areAllNumbers(creditCardNumber) &&
                luhnAlgorithmValidator(creditCardNumber);
    }

    /*
    checks if credit card number is in the range of 0 and 16
     */
    private Boolean isCreditCardNumbersBounded(String creditCardNumber) {

        int numberOfDigits = creditCardNumber.length();
        return (numberOfDigits > 0 && numberOfDigits <= 16);
    }

    /*
    checks if credit card number contains all numeric characters.
    method also used to check if credit limit contains all numeric characters.
     */
    private Boolean areAllNumbers(String creditCardNumber) {
        for (int i = creditCardNumber.length() - 1; i >= 0; i--) {
            if (creditCardNumber.charAt(i) < 48 || creditCardNumber.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }

    /*
    Luhn 10 algorithm implementation. Checks if credit card number is valid.
     */
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
