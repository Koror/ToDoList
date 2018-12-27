package com.koror.app.error;

public class WrongInputException extends RuntimeException {

    public WrongInputException() {

    }

    @Override
    public String getMessage() {
        return "Wrong input";
    }




}
