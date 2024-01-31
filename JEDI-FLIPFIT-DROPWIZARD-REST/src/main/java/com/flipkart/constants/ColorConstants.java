/**
 *
 */
package com.flipkart.constants;

/**
 *
 */
public class ColorConstants {
	public static String RED = "\u001B[31m";
	public static String GREEN = "\u001B[32m";
	public static String YELLOW = "\u001B[33m";
	public static String BLUE = "\u001B[34m";
	public static String PURPLE = "\u001B[35m";
	public static String CYAN = "\u001B[36m";
	public static String WHITE = "\u001B[37m";

	public static String TEAL="\u001B[38;2;0;128;128m";
	public static String RESET = "\u001B[0m";
	public static String GREEN_COLOR = "\u001B[32m";
	public static String RED_COLOR = "\u001B[31m";
	public static String RESET_COLOR = "\u001B[0m";
	public static String BLUE_COLOR="\u001B[34m";
	public static String MAGENTA_COLOR="\u001b[35m";
	public static String ORANGE_COLOR="\u001B[38;2;255;165;0m";
	public static String CYAN_COLOR="\u001B[36m";
	public static String VIOLET_COLOR="\u001B[38;2;138;43;226m";
	public static String BOLD_TEXT = "\u001B[1m";
	public static String YELLOW_COLOR = "\u001B[33m";
	public static String DASHED_LINE = GREEN_COLOR +"------------------------------------------------------------------------" + RESET_COLOR;
	public static String INVALID_CHOICE_ERROR = RED_COLOR +  "\nPlease enter valid choice\n" + RESET_COLOR;
	public static String EXIT_MESSAGE = RED_COLOR + "\nEXITING THE APPLICATION\n" + RESET_COLOR;

	public static String PREVIOUS_MENU_MESSAGE = "\nGOING BACK TO PREVIOUS MENU\n";
	public static String WELCOME_MESSAGE = BLUE_COLOR + BOLD_TEXT +
			"░█▀▀░█░░░▀█▀░█▀█░█▀▀░▀█▀░▀█▀\n"+
			"░█▀▀░█░░░░█░░█▀▀░█▀▀░░█░░░█░\n"+
			"░▀░░░▀▀▀░▀▀▀░▀░░░▀░░░▀▀▀░░▀░\n"+RESET_COLOR;
	public static String APPROVAL_GYM_CENTRE_CONFIRMATION =GREEN_COLOR + "\nAdmin Approved the Gym Centre\n"+ RESET_COLOR;
	public static String DISAPPROVAL_GYM_CENTRE_CONFIRMATION =RED_COLOR +  "\nAdmin Disapproved the Gym Centre\n"+ RESET_COLOR;
	public static String APPROVAL_GYM_OWNER_CONFIRMATION = GREEN_COLOR + "\nAdmin Approved the Gym Owner\n"+ RESET_COLOR;
	public static String DISAPPROVAL_GYM_OWNER_CONFIRMATION =RED_COLOR +  "\nAdmin Disapproved the Gym Owner\n"+ RESET_COLOR;
	public static String INVALID_SLOT = RED_COLOR + "PLEASE CHOOSE A VALID SLOT"+RESET_COLOR;
	public static String Admin_login_failed=RED_COLOR+"ADMIN login failed"+RESET_COLOR;
	public static String Admin_login_success=GREEN_COLOR+"Successfully logged in"+RESET_COLOR;
}