package com.koror.app.error;

public class UserNotExistsException extends RuntimeException {

    public UserNotExistsException() {
        super("User doesn't exist");
    }

}
