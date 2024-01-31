package com.flipkart.exception;

import static com.flipkart.constants.ColorConstants.*;

public class LogInFailedException extends RuntimeException{
    public LogInFailedException(){
        super(RED+"Unable to login, Check your username and password."+RESET);
}
}
