package com.flipkart.exception;

import static com.flipkart.constants.ColorConstants.RED_COLOR;
import static com.flipkart.constants.ColorConstants.RESET_COLOR;

public class WrongCredentialsException extends Exception {
    public WrongCredentialsException() {
        super(RED_COLOR+"Wrong username or password!"+RESET_COLOR);
    }
}