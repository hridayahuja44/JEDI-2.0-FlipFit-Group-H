package com.flipkart.exception;


import static com.flipkart.constants.ColorConstants.RED_COLOR;
import static com.flipkart.constants.ColorConstants.RESET_COLOR;

    public class BookingFailedException extends RuntimeException{
        public BookingFailedException(String message){
            super(RED_COLOR+message+RESET_COLOR);
        }
    }


