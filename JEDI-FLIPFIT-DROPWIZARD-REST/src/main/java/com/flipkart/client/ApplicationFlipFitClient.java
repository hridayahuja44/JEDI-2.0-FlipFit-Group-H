package com.flipkart.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.flipkart.bean.User;
import com.flipkart.service.UserFlipFitServiceImpl;
import com.flipkart.constants.*;
import com.flipkart.exception.UserNotFoundException;
import static com.flipkart.constants.ColorConstants.*;

public class ApplicationFlipFitClient {

	public static void login() throws Exception {
		Scanner in = new Scanner(System.in);

		LocalDateTime currentDateTime = LocalDateTime.now();

		// Format the date and time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		System.out.println( "----------------------------------------------------------------");
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println(ColorConstants.BLUE + "|                        USER LOGIN                            |" + ColorConstants.RESET);
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println( "----------------------------------------------------------------" );

		System.out.print(ColorConstants.YELLOW+"Enter your Email Address: "+ColorConstants.RESET);
		String userEmail = in.next();
		System.out.print(ColorConstants.YELLOW+"Enter your Password: "+ColorConstants.RESET);
		String password = in.next();
		System.out.print(ColorConstants.YELLOW+"Choose your role: "+"\n1. Gym Customer\n2. Gym Owner\n3. System Admin\n "+ColorConstants.RESET);
		String roleCode = in.next();
		String roleId = "Admin";
		if (roleCode.equals("1")) // tokenizing the role id
		{
			roleId = "Customer";
		}
		else if (roleCode.equals("2")) {
			roleId = "GymOwner";
		}
		else if(roleCode.equals("3"))
		{
			roleId = "Admin";
		}
		else
		{
			System.out.println(ColorConstants.RED + "You've entered an invalid option."+ ColorConstants.RESET);
			return;
//			throw InvalidInputException("Exiting");
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
			System.out.println("----------------------------------------------------------------");
			System.out.println(
					ColorConstants.BOLD_TEXT + ColorConstants.GREEN + "Hi! " + userEmail + ". You are now logged in. " + "(" + formattedDateTime + ")"
							+ ColorConstants.RESET);

			if (roleId.equalsIgnoreCase("Customer")) {

				CustomerFlipFitClient customer = new CustomerFlipFitClient();
				customer.customerMenu(userEmail);

			} else if (roleId.equalsIgnoreCase("GymOwner")) {

				GymOwnerFlipFitClient gymOwner = new GymOwnerFlipFitClient();
				gymOwner.gymOwnerMenu(in, userEmail);

			} else {
				System.out.println(ColorConstants.RED + "You've entered an invalid option!" + ColorConstants.RESET);
			}
		} catch (UserNotFoundException e) {
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
		}
	}

	public static void applicationMenu() throws Exception {
		boolean recur = true;

		int choice = 0;
		while (choice != 4){
			System.out.println("\033[3m");
			System.out.println("\033[1;34m███████ ██       ██  ██████ \033[0m" + "\033[1;36m███████ ██ ████████\033[0m");
			System.out.println("\033[1;34m██      ██       ██  ██  ██ \033[0m" + "\033[1;36m██      ██    ██\033[0m");
			System.out.println("\033[1;34m█████   ██       ██  ██████ \033[0m" + "\033[1;36m█████   ██    ██\033[0m");
			System.out.println("\033[1;34m██      ██       ██  ██     \033[0m" + "\033[1;36m██      ██    ██\033[0m");
			System.out.println("\033[1;34m██      ██       ██  ██     \033[0m" + "\033[1;36m██      ██    ██\033[0m");
			System.out.println("\033[1;34m██      ███████  ██  ██     \033[0m" + "\033[1;36m██      ██    ██ \033[0m");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("----------------------------------------------------------------" );
			System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
			System.out.println(ColorConstants.BLUE + "|                WELCOME TO FLIPFIT APPLICATION                |" + ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
			System.out.println("----------------------------------------------------------------" );

			System.out.println(ColorConstants.YELLOW+"\nPlease enter your desired option: "+ColorConstants.RESET);
			System.out.println();
			System.out.println(ColorConstants.YELLOW+"1. Login"+ ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW+"2. Customer Registration"+ ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW+"3. Gym Owner Registration"+ ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW+"4. Exit"+ ColorConstants.RESET);
			System.out.print("\nPlease enter your desired option: ");

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
					System.out.println(ColorConstants.GREEN + "You've successfully exited" + ColorConstants.RESET);
					recur = false;
					System.exit(0);
					break;
				default:
					System.out.println(ColorConstants.RED + "You've entered an invalid option!" + ColorConstants.RESET);
			}
		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		applicationMenu();
	}

}