package com.flipkart.exception;

public class GymOwnerNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Sorry, gym owner is not found with the given details!";
    }
}
