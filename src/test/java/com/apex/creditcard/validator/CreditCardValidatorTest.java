package com.apex.creditcard.validator;

import com.apex.creditcard.dao.CreditCardDao;
import com.apex.creditcard.model.CreditCard;
import com.apex.creditcard.model.CreditCardRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditCardValidatorTest {

    private final CreditCardValidator validator = new CreditCardValidator();
    private final CreditCardDao dao = Mockito.mock(CreditCardDao.class);

    @Test
    public void testCardLimit() {

        assertTrue(validator.areCreditLimitAllNumeric("123"));

        assertFalse(validator.areCreditLimitAllNumeric("sad123"));
        assertFalse(validator.areCreditLimitAllNumeric("  asd"));
        assertFalse(validator.areCreditLimitAllNumeric("  134"));
        assertFalse(validator.areCreditLimitAllNumeric(" -- 134"));
    }

    @Test
    public void testCreditCardNumber() {

        assertTrue(validator.isValidCreditCardNumber("18"));
        assertTrue(validator.isValidCreditCardNumber("7992-7398-713"));

        assertFalse(validator.isValidCreditCardNumber("128"));
        assertFalse(validator.isValidCreditCardNumber("1281-3214-1232-1234"));
        assertFalse(validator.isValidCreditCardNumber("1281-3214-1232-1234-1234"));

        assertFalse(validator.isValidCreditCardNumber("1asd8"));
        assertFalse(validator.isValidCreditCardNumber("18ads"));
        assertFalse(validator.isValidCreditCardNumber("18ads  "));
        assertFalse(validator.isValidCreditCardNumber(""));
    }

    @Test
    public void testIfCardAlreadyExistsPositive() {

        CreditCardValidator validator = new CreditCardValidator(dao);
        CreditCardRequest request = new CreditCardRequest();
        request.setCardName("John");
        request.setCardNumber("18");
        request.setLimit("5000");

        CreditCard savedCard = CreditCard.buildCreditCard(request);
        Mockito.when(dao.findByNumber(Mockito.anyString())).thenReturn(savedCard);

        assertTrue(validator.doesCardNumberAlreadyExists("18"));
    }

    @Test
    public void testIfCardAlreadyExistsNegative() {

        CreditCardValidator validator = new CreditCardValidator(dao);
        Mockito.when(dao.findByNumber(Mockito.anyString())).thenReturn(null);
        assertFalse(validator.doesCardNumberAlreadyExists("18"));
    }
}
