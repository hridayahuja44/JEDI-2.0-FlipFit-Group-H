package com.flipkart.client;

import java.util.*;

import com.flipkart.bean.User;
import com.flipkart.constants.*;
import com.flipkart.exception.UserNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.flipkart.service.UserFlipFitServiceImpl;

public class ApplicationFlipFitClient {

	public static void login() throws Exception {
		Date date = new Date();
		LocalDateTime currentDateTime=LocalDateTime.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime= currentDateTime.format(formatter);
		Scanner in = new Scanner(System.in);

		System.out.println("\n-----------------------------------------");
		System.out.println(ColorConstants.BLUE + "            LOGIN DETAILS                " + ColorConstants.RESET);
		System.out.println("-----------------------------------------");
		System.out.print(ColorConstants.CYAN + "Enter your Email address: " + ColorConstants.RESET);
		String userEmail = in.next();
		System.out.print(ColorConstants.CYAN + "Enter your Password: " + ColorConstants.RESET);
		String password = in.next();
		System.out.println("Enter your ID for your role: ");
		System.out.println(ColorConstants.CYAN + "1. Gym Customer" + ColorConstants.RESET);
		System.out.println(ColorConstants.CYAN + "2. Gym Owner" + ColorConstants.RESET);
		System.out.println(ColorConstants.CYAN + "3. System Admin" + ColorConstants.RESET);
		String roleCode = in.next();
		String roleId = "Admin";
		if (roleCode.equals("1")) // tokenizing the role id
		{
			roleId = "Customer";
		}
		if (roleCode.equals("2")) {
			roleId = "GymOwner";
		}
		User user = new User(userEmail, password, roleId);
		UserFlipFitServiceImpl userBusiness = new UserFlipFitServiceImpl();
		if (roleId.equalsIgnoreCase("Admin")) {
			AdminFlipFitClient admin = new AdminFlipFitClient();
			admin.adminMenu(in);
			return;
		}
		try {
			userBusiness.authenticateUser(user);
			System.out.println("-----------------------------------------");
			System.out.println(
					ColorConstants.GREEN + "Hi! " + userEmail + ". You have logged in at " + formattedDateTime
							+ ColorConstants.RESET);

			if (roleId.equalsIgnoreCase("Customer")) {

				CustomerFlipFitClient customer = new CustomerFlipFitClient();
				customer.customerMenu(userEmail);

			} else if (roleId.equalsIgnoreCase("GymOwner")) {

				GymOwnerFlipFitClient gymOwner = new GymOwnerFlipFitClient();
				gymOwner.gymOwnerMenu(in, userEmail);

			} else {
				System.out.println(ColorConstants.RED + "You've entered a wrong option!" + ColorConstants.RESET);
			}
		} catch (UserNotFoundException e) {
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
		}
	}

	public static void applicationMenu() throws Exception {
		boolean recur = true;

		int choice = 0;
		while (choice != 4) {
			System.out.println("---------------------------------------------------");
			System.out.println(String.format("%s\033[1mHELLO USER, WELCOME TO THE FLIPFIT APPLICATION!\033[0m%s",
					ColorConstants.BLUE, ColorConstants.RESET));
			System.out.println("---------------------------------------------------");
			System.out.println(ColorConstants.BLUE + "\nWhat would you like to do today?:" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "1. Login" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "2. Customer Registration" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "3. Gym Owner Registration" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "4. Exit" + ColorConstants.RESET);
			System.out.print("\nWhat is your choice: ");

			Scanner in = new Scanner(System.in);

			choice = in.nextInt();
			in.nextLine();
			switch (choice) {
				case 1:
					login();
					break;
				case 2:
					CustomerFlipFitClient customer = new CustomerFlipFitClient();
					customer.registerCustomer();
					login();
					break;
				case 3:
					GymOwnerFlipFitClient owner = new GymOwnerFlipFitClient();
					owner.gymOwnerRegistration(in);
					login();
					break;
				case 4:
					System.out.println(ColorConstants.GREEN + "EXITING..." + ColorConstants.RESET);
					System.out.println(ColorConstants.GREEN + "You've successfully exited!" + ColorConstants.RESET);
					recur = false;
					System.exit(0);
					break;
				default:
					System.out.println(ColorConstants.RED + "You've entered a wrong option!" + ColorConstants.RESET);
			}
		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		applicationMenu();
	}

}
