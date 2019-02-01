package com.koror.app.error;

public class WrongDataException extends RuntimeException{

    public WrongDataException() {
        super("Data wrong or null");
    }
}
