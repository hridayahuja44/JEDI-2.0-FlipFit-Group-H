package com.flipkart.exception;

public class UnauthorizedAccessException extends Exception {
    @Override
    public String getMessage() {
        return "Sorry, you are not authorized to make this change!";
    }
}