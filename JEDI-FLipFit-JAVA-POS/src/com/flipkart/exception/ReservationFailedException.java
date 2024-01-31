package com.flipkart.exception;

import static com.flipkart.constants.ColorConstants.RED;
import static com.flipkart.constants.ColorConstants.RESET;
public class ReservationFailedException extends RuntimeException{
    public ReservationFailedException(String message){
        super(RED+message+RESET);
    }

}
