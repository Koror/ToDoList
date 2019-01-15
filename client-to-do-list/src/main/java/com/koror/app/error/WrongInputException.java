package com.koror.app.error;

public class WrongInputException extends RuntimeException {

    public WrongInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongInputException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
