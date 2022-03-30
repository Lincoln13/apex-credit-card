package com.apex.creditcard.controller;

import com.apex.creditcard.exception.BusinessException;
import com.apex.creditcard.model.CreditCard;
import com.apex.creditcard.model.CreditCardRequest;
import com.apex.creditcard.model.Response;
import com.apex.creditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aon")
public class AonEndpoints {

    @Autowired
    private CreditCardService creditCardService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response> getAllCreditCards() {

        Response response = new Response();
        HttpStatus status = HttpStatus.OK;
        try {
            List<CreditCard> creditCards = creditCardService.getCreditCards();
            response.setCreditCards(creditCards);
        } catch (Exception e) {
            response.setMessage("Something went wrong!");
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(response);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response> saveCreditCard(@RequestBody CreditCardRequest request) {

        Response response = new Response();
        HttpStatus status = HttpStatus.OK;
        List<CreditCard> creditCards = new ArrayList();
        try {
            CreditCard creditCard = creditCardService.processCreditCard(request);
            creditCards.add(creditCardService.saveCreditCard(creditCard));
            response.setCreditCards(creditCards);
            response.setMessage("Card saved!");
        } catch (BusinessException exception) {
            response.setMessage(exception.getMessage());
            exception.printStackTrace();
            status = HttpStatus.BAD_REQUEST;
        } catch (Exception exception) {
            response.setMessage("Something went wrong!");
            exception.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(response);
    }
}
