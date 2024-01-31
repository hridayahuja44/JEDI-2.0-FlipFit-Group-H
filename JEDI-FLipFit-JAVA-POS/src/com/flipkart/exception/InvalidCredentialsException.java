package com.flipkart.exception;

import static com.flipkart.constants.ColorConstants.RED;
import static com.flipkart.constants.ColorConstants.RESET;
public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException () {
        super(RED+"Wrong username or password!"+RESET);
    }
}
