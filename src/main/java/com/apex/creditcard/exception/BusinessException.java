package com.apex.creditcard.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
