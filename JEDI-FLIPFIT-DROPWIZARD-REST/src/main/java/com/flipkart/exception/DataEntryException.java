package com.flipkart.exception;
import static com.flipkart.constants.ColorConstants.RED_COLOR;
import static com.flipkart.constants.ColorConstants.YELLOW_COLOR;

    public class DataEntryException extends RuntimeException{
        public DataEntryException(){
            super(RED_COLOR+"Incorrect Data format. Please refer to the format."+YELLOW_COLOR);
        }
    }