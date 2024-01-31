package com.flipkart.exception;

public class UserAlreadyExistsException extends Exception{
    @Override
    public String getMessage() {return "User already exists!";
    }
}