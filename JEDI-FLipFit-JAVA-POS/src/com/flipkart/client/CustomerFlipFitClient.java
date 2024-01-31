package com.flipkart.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.constants.ColorConstants;
import com.flipkart.exception.CustomerNotFoundException;
import com.flipkart.exception.SlotNotFoundException;
import com.flipkart.exception.UserAlreadyExistsException;
import com.flipkart.validator.EmailFlipFitValidator;
import com.flipkart.validator.CardNumberFlipFitValidator;
import com.flipkart.service.CustomerFlipFitServiceImpl;
import com.flipkart.service.UserFlipFitServiceImpl;

public class CustomerFlipFitClient {

	Customer customer = new Customer();
	CustomerFlipFitServiceImpl customerBusiness = new CustomerFlipFitServiceImpl();
	Scanner sc = new Scanner(System.in);

	public void registerCustomer() throws UserAlreadyExistsException {
		System.out.println("------------------------------------------");
		System.out.println(ColorConstants.BLUE + "            CUSTOMER REGISTRATION         " + ColorConstants.RESET);
		System.out.println("------------------------------------------");
		String email="",phoneNo="";
//		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher m = pattern.matcher(email);
		boolean isEmailCorrect = false;

		while(!isEmailCorrect){
			System.out.print(ColorConstants.CYAN + "Enter your Email ID: " + ColorConstants.RESET);
			email = sc.next();
			isEmailCorrect= EmailFlipFitValidator.isEmailCorrect(email);
		}
		customer.setEmail(email);
//		System.out.print(ColorConstants.CYAN + "Enter Email: " + ColorConstants.RESET);
//		customer.setEmail(sc.next());
		System.out.print(ColorConstants.CYAN + "Enter your Password: " + ColorConstants.RESET);
		customer.setPassword(sc.next());
		customer.setRoleId("Customer");
		System.out.print(ColorConstants.CYAN + "Enter your Name: " + ColorConstants.RESET);
		customer.setName(sc.next());
		while(!CardNumberFlipFitValidator.isLengthCorrect(phoneNo,10)){
			if(!phoneNo.isEmpty())System.out.println(ColorConstants.RED + "This is an invalid Phone number" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "Enter your Phone Number" + ColorConstants.RESET);
			phoneNo = sc.next();
		}
		customer.setPhoneNumber(phoneNo);
		System.out.print(ColorConstants.CYAN + "Enter your Age: " + ColorConstants.RESET);
		customer.setAge(Integer.valueOf(sc.next()));
		System.out.print(ColorConstants.CYAN + "Enter your Address: " + ColorConstants.RESET);
		customer.setAddress(sc.next());
		UserFlipFitServiceImpl userBusiness = new UserFlipFitServiceImpl();
		userBusiness.registerCustomer(customer);

		System.out.println(ColorConstants.GREEN + "Wohoo! You've been successfully registered!" + ColorConstants.RESET);

	}

	public void getProfile(String email) {
		try {
			customer = customerBusiness.getProfile(email);
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
			return;
		}
		System.out.println("------------------------------------------------------------");
		System.out.printf(ColorConstants.BLUE + "%15s%15s%15s%15s", "Customer Name", "Phone Number", "Address", "Age" + ColorConstants.RESET);
		System.out.println();
		System.out.printf("%15s%15s%15s%15s", customer.getName(), customer.getPhoneNumber(), customer.getAddress(),
				customer.getAge());
		System.out.println();
		System.out.println("\n-----------------------------------------------------------");
	}

	public void viewGyms(String email) throws ParseException, SlotNotFoundException {
		getGyms();
		System.out.print(ColorConstants.CYAN + "Enter the Gym ID: " + ColorConstants.RESET);
		String gymId = sc.next();
		System.out.print(ColorConstants.CYAN + "Enter Date in the format - (yyyy-mm-dd): " + ColorConstants.RESET);
		String dateStr = sc.next();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(dateStr);

		List<Slot> slots = customerBusiness.getSlotInGym(gymId);
		for (Slot slot : slots) {
			System.out.println(ColorConstants.CYAN + "\nSlot Id: " + slot.getSlotId() + ColorConstants.RESET);
//			System.out.println(ColorConstants.CYAN + "Availability: " + customerBusiness.isSlotBooked(slot.getSlotId(), String.valueOf(date)) + ColorConstants.RESET);
		}
		System.out.print(ColorConstants.CYAN + "Enter Slot ID for the seat which you want to book: " + ColorConstants.RESET);
		String slotId = sc.next();
		int bookingResponse = customerBusiness.bookSlot(gymId,slotId, email, String.valueOf(date));
		switch (bookingResponse) {
			case 0:
				System.out.println(ColorConstants.CYAN + "Since you have already booked this time, we are overriding the previous slot with this one." + ColorConstants.RESET);
				break;
			case 1:
				System.out.println(ColorConstants.CYAN + "Since this slot is already booked, we've added you to the waiting list." + ColorConstants.RESET);
				break;
			case 2:
				System.out.println(ColorConstants.GREEN + "The slot has been Successfully booked." + ColorConstants.RESET);
				break;
			case 3:
				System.out.println(ColorConstants.RED + "SLOT NOT FOUND" + ColorConstants.RESET);
				break;
			default:
				System.out.println(ColorConstants.RED + "FAILURE ENCOUNTERED!" + ColorConstants.RESET);
		}
	}
//	public void editProfile(String email) throws CustomerNotFoundException {
//		System.out.println("==========================================");
//		System.out.println("              Edit Profile               ");
//		System.out.println("==========================================");
//		System.out.println("Want to change password? Yes/No");
//		String choice = sc.next();
//		Customer customer = new Customer();
//		try{
//			Customer existingCustomer = customerBusiness.getProfile(email);
//			if (existingCustomer != null) {
//				// Use existing values as default values
//				customer.setEmail(existingCustomer.getEmail());
//				customer.setName(existingCustomer.getName());
//				customer.setPhoneNumber(existingCustomer.getPhoneNumber());
//				customer.setAddress(existingCustomer.getAddress());
//				customer.setAge(existingCustomer.getAge());
//
//			}
//		}catch (Error e){
//			System.out.println("Bad issue");
//		}
//
//		try{
//			UserFlipFitDAOImpl u1 = new UserFlipFitDAOImpl();
//			customer.setPassword(u1.getPassword(customer.getEmail()));
//		}catch (Error e){
//			System.out.println("Bad issue");
//			return ;
//		}
//
//		if(choice.equals("Yes")){
//			System.out.print("Enter Password: ");
//			customer.setPassword(sc.next());
//		}
//		System.out.println("Want to change name? Yes/No");
//		choice = sc.next();
//		if(choice.equals("Yes")) {
//			System.out.print("Enter Name: ");
//			customer.setName(sc.next());
//		}
//		System.out.println("Want to change phone number? Yes/No");
//		choice = sc.next();
//		if(choice.equals("Yes")) {
//			System.out.print("Enter Phone Number: ");
//			customer.setPhoneNumber(sc.next());
//		}
//		System.out.println("Want to change age? Yes/No");
//		choice = sc.next();
//		if(choice.equals("Yes")) {
//			System.out.print("Enter Age: ");
//			customer.setAge(Integer.valueOf(sc.next()));
//		}
//		System.out.println("Want to change address? Yes/No");
//		choice = sc.next();
//		if(choice.equals("Yes")) {
//			System.out.print("Enter Address: ");
//			customer.setAddress(sc.next());
//		}
//		try{
//			customerBusiness.editProfile(customer);
//		}catch (Error e){
//			return ;
//		}
//		System.out.println("Successfully edited your profile");
//	}
	public void editProfile(String email) {
		System.out.println("------------------------------------------");
		System.out.println(ColorConstants.BLUE + "              Edit Profile               " + ColorConstants.RESET);
		System.out.println("------------------------------------------");
		System.out.println("Would you like to change your password? Yes/No");
		String choice = sc.next();
		if(choice.equals("Yes")){
			System.out.print(ColorConstants.CYAN + "Enter updated Password: " + ColorConstants.RESET);
			customer.setPassword(sc.next());
		}
		System.out.println("Would you like to change your name? Yes/No");
		choice = sc.next();
		if(choice.equals("Yes")) {
			System.out.print(ColorConstants.CYAN + "Enter updated name: " + ColorConstants.RESET);
			customer.setName(sc.next());
		}
		System.out.println("Would you like to change your phone number? Yes/No");
		choice = sc.next();
		if(choice.equals("Yes")) {
			System.out.print(ColorConstants.CYAN + "Enter updated Phone Number: " + ColorConstants.RESET);
			customer.setPhoneNumber(sc.next());
		}
		System.out.println("Would you like to change your age? Yes/No");
		choice = sc.next();
		if(choice.equals("Yes")) {
			System.out.print(ColorConstants.CYAN + "Enter updated age: " + ColorConstants.RESET);
			customer.setAge(Integer.valueOf(sc.next()));
		}
		System.out.println("Would you like to change your address? Yes/No");
		choice = sc.next();
		if(choice.equals("Yes")) {
			System.out.print(ColorConstants.CYAN + "Enter updated address: " + ColorConstants.RESET);
			customer.setAddress(sc.next());
		}
		System.out.println(ColorConstants.GREEN + "Your profile has been successfully edited!" + ColorConstants.RESET);
	}

	public void getGyms() {
		System.out.print(ColorConstants.CYAN + "Enter your location: " + ColorConstants.RESET);
		List<Gym> gyms = customerBusiness.getGymInCity(sc.next());
//		for (Gym gym : gyms) {
//			System.out.println(ColorConstants.CYAN + "Gym Id: "  + ColorConstants.RESET + gym.getGymId());
//			System.out.println(ColorConstants.CYAN + "Gym Owner Email: " + ColorConstants.RESET + gym.getOwnerEmail());
//			System.out.println(ColorConstants.CYAN + "Gym Name: " + ColorConstants.RESET + gym.getGymName());
//			System.out.println();
//		}

		gyms.forEach(gym -> {
			System.out.printf(ColorConstants.CYAN+"%15s%20s%15s", gym.getGymId(), gym.getOwnerEmail(), gym.getGymName()+ColorConstants.RESET);
			System.out.println();
			System.out.println("==========================================================================");
			System.out.println();
		});
	}

	public void cancelBooking(String email) {
		System.out.println("-----------------------------------------");
		System.out.println(ColorConstants.BLUE + "            Cancel Booking               " + ColorConstants.RESET);
		System.out.println("-----------------------------------------");
		System.out.print(ColorConstants.CYAN + "Enter ID of the booking you want to cancel: " + ColorConstants.RESET);
		String bookingId = sc.next();
		customerBusiness.cancelBooking(bookingId, email);
	}

	public void customerMenu(String email) throws ParseException, SlotNotFoundException {
		int choice = 0;

		while (choice != 6) {
			System.out.println("-----------------------------------------");
			System.out.println(ColorConstants.BLUE + "                 Menu                    " + ColorConstants.RESET);
			System.out.println("-----------------------------------------");
			System.out.println(ColorConstants.CYAN + "1.View all Gyms \n2.View Booked Slots \n3.Cancel Booked Slots \n4.Edit User Profile \n5.View User Profile \n6.Exit" + ColorConstants.RESET);
			System.out.print(ColorConstants.CYAN + "Please enter your choice: " + ColorConstants.RESET);
			choice = sc.nextInt();

			switch (choice) {
				case 1:
					viewGyms(email);
					break;
				case 2:
					customerBusiness.getBookings(email);
					break;
				case 3:
					cancelBooking(email);
					break;
				case 4:
					editProfile(email);
					break;
				case 5:
//					getProfile(email);
					getProfile(email);
				case 6:
					break;
				default:
					System.out.println(ColorConstants.RED + "You've selected an invalid option!" + ColorConstants.RESET);
			}
		}
	}
}