package com.flipkart.exception;

public class UserNotFoundException extends Exception {
    @Override
    public String getMessage(){
        return "Wrong credentials entered! Please recheck!";
    }
}


