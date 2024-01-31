package com.flipkart.validator;

public class LengthFlipfitValidator {
    public static boolean isLengthCorrect(String txt,int length){
        if(txt.length() == length){
            return true;
        }
        return false;
    }
}
