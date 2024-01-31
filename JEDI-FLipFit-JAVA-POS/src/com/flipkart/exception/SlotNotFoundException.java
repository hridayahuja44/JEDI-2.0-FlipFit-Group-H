package com.flipkart.exception;

public class SlotNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Sorry, no slot is found!";
    }
}
