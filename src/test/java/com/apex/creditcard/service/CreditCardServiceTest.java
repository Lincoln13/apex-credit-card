package com.apex.creditcard.service;

import com.apex.creditcard.dao.CreditCardDao;
import com.apex.creditcard.exception.BusinessException;
import com.apex.creditcard.model.CreditCard;
import com.apex.creditcard.model.CreditCardRequest;
import com.apex.creditcard.service.impl.CreditCardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardServiceTest {

    private CreditCardService service = new CreditCardServiceImpl();
    private CreditCardDao dao = Mockito.mock(CreditCardDao.class);


    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(service, "creditCardDao", dao);
    }

    @Test
    public void testProcessCreditCardPositive() {

        CreditCardRequest request = new CreditCardRequest();
        request.setCardName("John");
        request.setCardNumber("18");
        request.setLimit("5000");

        CreditCard transformedCard = service.processCreditCard(request);

        assertEquals(transformedCard.getName(), "John");
        assertEquals(transformedCard.getNumber(), "18");
        assertEquals(transformedCard.getLimit(), BigDecimal.valueOf(5000));
        assertEquals(transformedCard.getBalance(), BigDecimal.ZERO);
    }

    @Test
    public void testIfCardIsNewPositive() {

        CreditCardRequest request = new CreditCardRequest();
        request.setCardName("John");
        request.setCardNumber("26");
        request.setLimit("5000");

        Mockito.when(dao.findByNumber(Mockito.anyString())).thenReturn(null);
        CreditCard transformedCard = service.processCreditCard(request);

        assertEquals(transformedCard.getName(), "John");
        assertEquals(transformedCard.getNumber(), "26");
        assertEquals(transformedCard.getLimit(), BigDecimal.valueOf(5000));
        assertEquals(transformedCard.getBalance(), BigDecimal.ZERO);
    }

    @Test
    public void testSaveCardToDB() {

        CreditCardRequest request = new CreditCardRequest();
        request.setCardName("John");
        request.setCardNumber("26");
        request.setLimit("5000");

        CreditCard savedCard = CreditCard.buildCreditCard(request);
        Mockito.when(dao.save(Mockito.any())).thenReturn(savedCard);
        CreditCard transformedCard = service.saveCreditCard(savedCard);

        assertEquals(transformedCard.getName(), "John");
        assertEquals(transformedCard.getNumber(), "26");
        assertEquals(transformedCard.getLimit(), BigDecimal.valueOf(5000));
        assertEquals(transformedCard.getBalance(), BigDecimal.ZERO);
    }

    @Test
    public void testGetCardFromDB() {

        CreditCardRequest request = new CreditCardRequest();
        request.setCardName("John");
        request.setCardNumber("26");
        request.setLimit("5000");

        CreditCard savedCard = CreditCard.buildCreditCard(request);
        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(savedCard);

        Mockito.when(dao.findAll()).thenReturn(creditCardList);
        List<CreditCard> transformedCards = service.getCreditCards();

        assertEquals(1, transformedCards.size());
        assertEquals(transformedCards.get(0).getName(), "John");
        assertEquals(transformedCards.get(0).getNumber(), "26");
        assertEquals(transformedCards.get(0).getLimit(), BigDecimal.valueOf(5000));
        assertEquals(transformedCards.get(0).getBalance(), BigDecimal.ZERO);
    }

    @Test
    public void testProcessCreditCardNegative() {

        CreditCardRequest request = getRequest();
        String actualMessage = "";
        String expectedMessage = "Invalid credit card number!";

        try {
            service.processCreditCard(request);
        } catch(BusinessException e) {
            actualMessage = e.getMessage();
        }
        assertEquals(actualMessage, expectedMessage);

        request.setCardNumber("13-o");
        try {
            service.processCreditCard(request);
        } catch(BusinessException e) {
            actualMessage = e.getMessage();
        }
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testInvalidCreditCardLimit() {

        CreditCardRequest request = getRequest();
        String actualMessage = "";
        String expectedMessage = "Invalid credit card limit!";
        request.setLimit("sab");
        try {
            service.processCreditCard(request);
        } catch(BusinessException e) {
            actualMessage = e.getMessage();
        }
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testIfCardAlreadyExistsNegative() {

        CreditCardRequest request = new CreditCardRequest();
        request.setCardName("John");
        request.setCardNumber("18");
        request.setLimit("5000");

        CreditCard savedCard = CreditCard.buildCreditCard(request);

        String actualMessage = "";
        String expectedMessage = "Card already added!";

        Mockito.when(dao.findByNumber(Mockito.anyString())).thenReturn(savedCard);
        try {
            service.processCreditCard(request);
        } catch(BusinessException e) {
            actualMessage = e.getMessage();
        }
        assertEquals(actualMessage, expectedMessage);
    }

    private static CreditCardRequest getRequest() {

        CreditCardRequest request = new CreditCardRequest();
        request.setCardName("John");
        request.setCardNumber("181");
        request.setLimit("5000");
        return request;
    }
}
