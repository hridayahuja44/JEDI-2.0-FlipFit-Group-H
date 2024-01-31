package com.flipkart.exception;

import static com.flipkart.constants.ColorConstants.*;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException(){
        super(RED_COLOR+"Unable to login, Check your username and password."+YELLOW_COLOR);
    }
}
