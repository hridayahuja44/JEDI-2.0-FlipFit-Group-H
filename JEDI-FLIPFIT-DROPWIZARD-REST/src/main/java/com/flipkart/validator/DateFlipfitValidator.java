package com.flipkart.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFlipfitValidator {
    public static boolean isValidFutureDate(String inputDate) {
        try {
            // Parse the input date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(inputDate, formatter);

            // Get today's date
            LocalDate currentDate = LocalDate.now();

            // Check if the parsed date is today or in the future
            return !date.isBefore(currentDate);
        } catch (Exception e) {
            // Handle parsing errors or invalid date format
            return false;
        }
    }
}
