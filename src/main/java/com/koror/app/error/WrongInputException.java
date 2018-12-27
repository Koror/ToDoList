package com.koror.app.error;

public class WrongInputException extends RuntimeException {

    public WrongInputException() {
    }

    public WrongInputException(String message) {
        super(message);
    }

}
