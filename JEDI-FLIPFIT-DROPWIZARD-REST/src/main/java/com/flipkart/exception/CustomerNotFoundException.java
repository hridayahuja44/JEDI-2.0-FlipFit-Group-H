package com.flipkart.exception;

public class CustomerNotFoundException extends Exception {
    @Override
    public String getMessage(){

        return "Sorry, Customer is not found with the given details!\"";
    }
}
