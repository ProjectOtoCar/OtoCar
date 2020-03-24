package com.otocar.otocar.exceptions;

public class ErrorJson {
    private String message;
    private String field;

    public ErrorJson(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public ErrorJson() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
