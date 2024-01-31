package com.flipkart.exception;
public class GymNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Sorry, Gym details not found!";
    }
}