package com.koror.app.dto;

public class Result {

    String result = "Error";

    public void success() {
        result = "Ok";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
