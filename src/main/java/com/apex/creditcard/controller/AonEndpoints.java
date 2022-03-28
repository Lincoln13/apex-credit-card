package com.apex.creditcard.controller;

import com.apex.creditcard.model.CreditCard;
import com.apex.creditcard.model.Response;
import com.apex.creditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aon")
public class AonEndpoints {

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping
    public ResponseEntity<Response> getAllCreditCards() {

        Response response = new Response();
        try {
            List<CreditCard> creditCards = creditCardService.getCreditCards();
            response.setCreditCardList(creditCards);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response> saveCreditCard(@RequestBody CreditCard creditCard) {

        Response response = new Response();
        try {
            CreditCard savedCreditCard = creditCardService.saveCreditCard(creditCard);
            response.setMessage("Saved card! " + savedCreditCard.toString());
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(response);
    }
}