package com.flipkart.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFlipfitValidator {

    public static boolean isEmailCorrect(String email){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(email);
        return m.matches();
    }

}
