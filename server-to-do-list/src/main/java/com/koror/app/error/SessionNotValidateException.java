package com.koror.app.error;

public class SessionNotValidateException extends RuntimeException {

    public SessionNotValidateException(){
        super("Session not validated");
    }

}
