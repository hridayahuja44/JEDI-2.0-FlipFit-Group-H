package com.flipkart.client;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.constants.ColorConstants;
import com.flipkart.exception.GymNotFoundException;
import com.flipkart.exception.GymOwnerNotFoundException;
import com.flipkart.exception.UnauthorizedAccessException;
import com.flipkart.exception.UserAlreadyExistsException;
import com.flipkart.utils.IdGenerator;
import com.flipkart.validator.EmailFlipFitValidator;
import com.flipkart.validator.CardNumberFlipFitValidator;
import com.flipkart.service.GymOwnerFlipFitServiceImpl;
import com.flipkart.service.UserFlipFitServiceImpl;

public class GymOwnerFlipFitClient {

	GymOwner gymOwner = new GymOwner();
	GymOwnerFlipFitServiceImpl gymOwnerBusiness = new GymOwnerFlipFitServiceImpl();
	UserFlipFitServiceImpl userBusiness = new UserFlipFitServiceImpl();

	public void gymOwnerRegistration(Scanner in) throws UserAlreadyExistsException {
		System.out.println("-------------------------------------------");
		System.out.println(ColorConstants.BLUE + "            Gym Owner Registration         " + ColorConstants.RESET);
		System.out.println("-------------------------------------------");
		System.out.println(ColorConstants.CYAN + "\nEnter Gym Owner Details: \n" + ColorConstants.RESET);
		String aadhar="",email="",phoneNo="",pan="";
//		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher m = pattern.matcher(email);
		boolean isEmailCorrect = false;
		while(!isEmailCorrect){
			System.out.print(ColorConstants.CYAN + "Enter your Email ID: " + ColorConstants.RESET);
			email = in.next();
			isEmailCorrect= EmailFlipFitValidator.isEmailCorrect(email);
		}
		gymOwner.setEmail(email);

		System.out.print(ColorConstants.CYAN + "Enter your Password: " + ColorConstants.RESET);
		gymOwner.setPassword(in.next());
		gymOwner.setRoleId("GymOwner");
		System.out.print(ColorConstants.CYAN + "Enter your Name: " + ColorConstants.RESET);
		gymOwner.setName(in.next());

		while(!CardNumberFlipFitValidator.isLengthCorrect(phoneNo,10)){
			if(!phoneNo.isEmpty())System.out.println(ColorConstants.RED + "You've entered an invalid Phone number!" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "Enter your Phone Number" + ColorConstants.RESET);
			phoneNo = in.next();
		}
		gymOwner.setPhoneNumber(phoneNo);

		while(!CardNumberFlipFitValidator.isLengthCorrect(phoneNo,12)){
			if(!aadhar.isEmpty())System.out.println(ColorConstants.RED + "You've entered an invalid Aadhaar Card Number" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "Enter your Aadhaar Card Number" + ColorConstants.RESET);
			aadhar = in.next();
		}
		gymOwner.setAadharNumber(aadhar);
		while(!CardNumberFlipFitValidator.isLengthCorrect(phoneNo,10)){
			if(!pan.isEmpty())System.out.println(ColorConstants.RED + "You've entered an invalid PAN Card Number" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "Enter your PAN Card Number" + ColorConstants.RESET);
			pan = in.next();
		}
		gymOwner.setPanNumber(pan);

		UserFlipFitServiceImpl userBusiness = new UserFlipFitServiceImpl();
        userBusiness.registerGymOwner(gymOwner);
        System.out
                .println("\n" + ColorConstants.GREEN + "You've been registered successfully!" + ColorConstants.RESET);
    }

	public void editProfile(Scanner in, String email) {
		try {
			gymOwner = gymOwnerBusiness.getProfile(email);
		} catch (GymOwnerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------------------------");
		System.out.println(ColorConstants.BLUE + "              Edit Profile               " + ColorConstants.RESET);
		System.out.println("-----------------------------------------");
		System.out.println("Would you like to change your Email? Yes/No");
		String choice = sc.next();
		if(choice.equals("Yes")){
			System.out.println(ColorConstants.CYAN + "Enter updated Email: " + ColorConstants.RESET);
			gymOwner.setEmail(in.next());
		}
		System.out.println("Would you like to change your Password? Yes/No");
		choice = sc.next();
		if(choice.equals("Yes")){
			System.out.print(ColorConstants.CYAN + "Enter updated Password: " + ColorConstants.RESET);
			gymOwner.setPassword(in.next());
		}
		System.out.println("Would you like to change your Name? Yes/No");
		choice = sc.next();
		if(choice.equals("Yes")){
			System.out.print(ColorConstants.CYAN + "Enter updated Name: " + ColorConstants.RESET);
			gymOwner.setRoleId("GymOwner");
			gymOwner.setName(in.next());
		}
		System.out.println("Would you like to change your Phone Number? Yes/No");
		choice = sc.next();
		if(choice.equals("Yes")) {
			System.out.print(ColorConstants.CYAN + "Enter updated Phone Number: " + ColorConstants.RESET);
			gymOwner.setPhoneNumber(in.next());
		}
		System.out.println("Would you like to change your PAN Card Number? Yes/No");
		choice = sc.next();
		if(choice.equals("Yes")) {
			System.out.print(ColorConstants.CYAN + "Enter your PAN Card Number: " + ColorConstants.RESET);
			gymOwner.setPanNumber(in.next());
		}
		System.out.println("Would you like to change your Aadhaar Card Number? Yes/No");
		choice = sc.next();
		if(choice.equals("Yes")) {
			System.out.print(ColorConstants.CYAN + "Enter your Aadhaar Card Number: " + ColorConstants.CYAN);
			gymOwner.setAadharNumber(in.next());
		}
		try {
			gymOwnerBusiness.editProfile(gymOwner);
		} catch (GymOwnerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
		}
	}

	public void viewProfile(Scanner in, String email) {
		try {
			gymOwner = gymOwnerBusiness.getProfile(email);
		} catch (GymOwnerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
			return;
		}
		System.out.println("----------------------------------------------------------------");
		System.out.printf(ColorConstants.BLUE + "%15s%15s%15s%15s", "Gym Owner Name", "Phone Number", "PAN Number", "Aadhaar Number" + ColorConstants.RESET);
		System.out.println();
		System.out.printf("%15s%15s%15s%15s", gymOwner.getName(), gymOwner.getPhoneNumber(), gymOwner.getPanNumber(),
				gymOwner.getAadharNumber());
		System.out.println();
		System.out.println("\n---------------------------------------------------------------");
	}

	public void addGym(Scanner in, String email) {
		System.out.println(ColorConstants.CYAN + "Please Enter the details of your Gym: " + ColorConstants.RESET);

		Gym gym = new Gym();
		gym.setGymId(IdGenerator.generateId("Gym"));
		System.out.print(ColorConstants.CYAN + "Enter your Gym Name: " + ColorConstants.RESET);
		gym.setGymName(in.next());
		gym.setOwnerEmail(email);
		System.out.print(ColorConstants.CYAN + "Enter the Address: " + ColorConstants.RESET);
		gym.setAddress(in.next());
		System.out.print(ColorConstants.CYAN + "Enter the count for Slots in your gym: " + ColorConstants.RESET);
		try {
			gym.setSlotCount(in.nextInt());
		} catch (InputMismatchException e) {
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
			return;
		}
		System.out.print(ColorConstants.CYAN + "Enter the number of seats in each slot: " + ColorConstants.RESET);
		gym.setSeatsPerSlotCount(in.nextInt());
		gym.setVerified(false);

		gymOwnerBusiness.addGym(gym);
	}

	public void editGym(Scanner in, String email) {
		System.out.println(ColorConstants.CYAN + "Please Enter Gym Details ");

		Gym gym = new Gym();
		System.out.print(ColorConstants.CYAN + "Enter the Gym Id: " + ColorConstants.RESET);
		gym.setGymId(in.next());
		System.out.print(ColorConstants.CYAN + "Enter the updated name of your Gym: " + ColorConstants.RESET);
		gym.setGymName(in.next());
		gym.setOwnerEmail(email);
		System.out.print(ColorConstants.CYAN + "Enter the updated address: " + ColorConstants.RESET);
		gym.setAddress(in.next());
		System.out.print(ColorConstants.CYAN + "Enter the updated count of slots in your gym: " + ColorConstants.RESET);
		try {
			gym.setSlotCount(in.nextInt());
			System.out.print(ColorConstants.CYAN + "Enter the updated seats per slot: " + ColorConstants.RESET);
			gym.setSeatsPerSlotCount(in.nextInt());
		} catch (InputMismatchException e) {
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
			return;
		}
		gym.setVerified(false);

		try {
			gymOwnerBusiness.editGym(gym);
		} catch (GymNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
		}
	}

	public void getGymDetails(Scanner in, String email) {
		List<Gym> gymDetails = gymOwnerBusiness.getGymDetail(email);
		if (gymDetails.size() == 0) {
			System.out.println(ColorConstants.RED + "Sorry, we could not find any gym for the entered details!" + ColorConstants.RED);
			return;
		}
		System.out.println("-------------------------------------------------------------------------");
		System.out.println();
		System.out.printf(ColorConstants.BLUE + "%15s%15s%15s%15s%15s%15s", "Gym Id", "Gym Name", "Address", "SlotCount", "SeatsPerSlot", "Verification" + ColorConstants.RESET);
		gymDetails.forEach(gym -> {
			System.out.println();
			System.out.printf("%15s%15s%15s%15s%15s%15s", gym.getGymId(), gym.getGymName(), gym.getAddress(),
					gym.getSlotCount(), gym.getSeatsPerSlotCount(),
					gym.isVerified() ? "Verified" : "Not Verified");
		});
		System.out.println();
		System.out.println("-------------------------------------------------------------------------");
	}

	public void addSlot(Scanner in, String email) {
		System.out.println(ColorConstants.CYAN + "Enter the details of Slot: " + ColorConstants.RESET);
		Slot slot = new Slot();
		slot.setSlotId(IdGenerator.generateId("Slot"));
		System.out.print(ColorConstants.CYAN + "Enter Gym Id:" + ColorConstants.RESET);
		slot.setGymId(in.next());
		System.out.print(ColorConstants.CYAN + "Enter date for the available Slot: " + ColorConstants.RESET);
		slot.setDate(in.next());
		System.out.print(ColorConstants.CYAN + "Enter Slot Start Time: " + ColorConstants.RESET);
		slot.setStartTime(in.next());
		System.out.print(ColorConstants.CYAN + "Enter Slot End Time: " + ColorConstants.RESET);
		slot.setEndTime(in.next());
		System.out.print(ColorConstants.CYAN + "Enter number of seats in slot: " + ColorConstants.RESET);
		try {
			slot.setNumOfSeats(in.nextInt());
		} catch (InputMismatchException e) {
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
			return;
		}
		System.out.print(ColorConstants.CYAN + "Enter the gym trainer: " + ColorConstants.RESET);
		slot.setTrainer(in.next());
		slot.setNumOfSeatsBooked(0);

		try {
			gymOwnerBusiness.addSlot(slot, email);
		} catch (GymNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
		} catch (UnauthorizedAccessException e) {
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
		}
	}

	public void gymOwnerMenu(Scanner in, String email) {
		boolean recur = true;
		while (recur) {
			System.out.println(ColorConstants.BLUE + "\nActions:" + ColorConstants.RESET);

			System.out.println(ColorConstants.CYAN + "1. View Gym Owner Profile" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "2. Edit Gym Owner Profile" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "3. Add a new Gym" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "4. Edit Gym details" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "5. Add a new Slot" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "6. View All Gym Details" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "7. LogOut\n" + ColorConstants.RESET);

			System.out.print(ColorConstants.BLUE + "Please enter your choice: " + ColorConstants.RESET);
			int choice = in.nextInt();

			System.out.println("----------------------------------------------------------------\n");

			switch (choice) {
				case 1:
					viewProfile(in, email);
					break;
				case 2:
					editProfile(in, email);
					break;
				case 3:
					addGym(in, email);
					break;
				case 4:
					editGym(in, email);
					break;
				case 5:
					addSlot(in, email);
					break;
				case 6:
					getGymDetails(in, email);
					break;
				case 7:
					recur = false;
					break;
				default:
					System.out.println(ColorConstants.RED + "You've entered an invalid option!" + ColorConstants.RESET);
			}
			if (!recur) {
				gymOwner = new GymOwner();
				boolean logOutSuccess = userBusiness.logout(gymOwner);
				if (logOutSuccess)
					System.out.println(ColorConstants.GREEN + "You've been successfully logged out!" + ColorConstants.RESET);
				else
					System.out.println(ColorConstants.RED + "You've been successfully logged out!" + ColorConstants.RESET);
			}
		}

	}

}
