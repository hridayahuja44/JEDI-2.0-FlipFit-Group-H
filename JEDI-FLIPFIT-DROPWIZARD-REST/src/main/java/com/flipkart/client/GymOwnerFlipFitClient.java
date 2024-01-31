package com.flipkart.client;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.flipkart.DAO.UserFlipFitDAOImpl;
import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.service.GymOwnerFlipFitServiceImpl;
import com.flipkart.service.UserFlipFitServiceImpl;
import com.flipkart.constants.ColorConstants;
import com.flipkart.exception.GymNotFoundException;
import com.flipkart.exception.GymOwnerNotFoundException;
import com.flipkart.exception.UnauthorizedAccessException;
import com.flipkart.exception.UserAlreadyExistsException;
import com.flipkart.utils.IdGenerator;
import com.flipkart.validator.EmailFlipfitValidator;
import com.flipkart.validator.LengthFlipfitValidator;

public class GymOwnerFlipFitClient {

	GymOwner gymOwner = new GymOwner();
	GymOwnerFlipFitServiceImpl gymOwnerBusiness = new GymOwnerFlipFitServiceImpl();
	UserFlipFitServiceImpl userBusiness = new UserFlipFitServiceImpl();

	public void gymOwnerRegistration(Scanner in) throws UserAlreadyExistsException {
		System.out.println( "----------------------------------------------------------------");
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println(ColorConstants.BLUE+"                          GYM OWNER REGISTRATION                    "+ColorConstants.RESET);
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println("----------------------------------------------------------------" );

		System.out.println(ColorConstants.YELLOW+"\nPlease enter the details for Gym Owner registration: \n" + ColorConstants.RESET);
		String aadhar="",email="",phoneNo="",pan="";
		boolean isEmailCorrect=false;
		while(isEmailCorrect==false){
			System.out.print(ColorConstants.YELLOW+"Enter your Email: "+ColorConstants.RESET);
			email = in.next();
			isEmailCorrect = EmailFlipfitValidator.isEmailCorrect(email);
		}
		gymOwner.setEmail(email);

		System.out.print(ColorConstants.YELLOW+"Enter your Password: "+ColorConstants.RESET);
		gymOwner.setPassword(in.next());
		gymOwner.setRoleId("GymOwner");
		System.out.print(ColorConstants.YELLOW+"Enter your Name: "+ColorConstants.RESET);
		gymOwner.setName(in.next());

		while(!LengthFlipfitValidator.isLengthCorrect(phoneNo,10)){
			if(!phoneNo.isEmpty())
				System.out.println( ColorConstants.RED+"You've entered an invalid Phone number"+ColorConstants.RESET);
			System.out.println( ColorConstants.YELLOW+"Enter your Phone Number"+ColorConstants.RESET);
			phoneNo = in.next();
		}
		gymOwner.setPhoneNumber(phoneNo);

		while(!LengthFlipfitValidator.isLengthCorrect(aadhar,12)){
			if(!aadhar.isEmpty())
				System.out.println(ColorConstants.RED+"You've entered an invalid Aadhaar Card Number"+ColorConstants.RESET);
			System.out.println( ColorConstants.YELLOW+"You've entered an invalid Aadhaar Card Number"+ColorConstants.RESET);
			aadhar = in.next();
		}
		gymOwner.setAadharNumber(aadhar);
		while(!LengthFlipfitValidator.isLengthCorrect(pan,10)){
			if(!pan.isEmpty())System.out.println(ColorConstants.RED+"You've entered an invalid PAN Card Number"+ColorConstants.RESET);
			System.out.println( ColorConstants.YELLOW+"Enter your PAN Number"+ColorConstants.RESET);
			pan = in.next();
		}
		gymOwner.setPanNumber(pan);

		UserFlipFitServiceImpl userBusiness = new UserFlipFitServiceImpl();
		userBusiness.registerGymOwner(gymOwner);
		System.out
				.println("\n" + ColorConstants.GREEN + "You've been successfully registered!" + ColorConstants.RESET);
	}

	public void editProfile(Scanner in, String email) {

		try {
			gymOwner = gymOwnerBusiness.getProfile(email);
		} catch (GymOwnerNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
			return;
		}
		try{
			UserFlipFitDAOImpl u1 = new UserFlipFitDAOImpl();

			gymOwner.setPassword(u1.getPassword(gymOwner.getEmail()));
		}catch (Error e){
			System.out.println(ColorConstants.BOLD_TEXT+ ColorConstants.RED+"Bad issue"+ColorConstants.RESET);
			return ;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println( "----------------------------------------------------------------" );
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println(ColorConstants.BLUE+"                             EDIT PROFILE                        "+ColorConstants.RESET);
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println("----------------------------------------------------------------");

		System.out.println(ColorConstants.YELLOW+"Would you like to edit your Email Address? Yes/No");
		String choice = sc.next();
		if(choice.equals("Yes")){
			System.out.println(ColorConstants.YELLOW+"Enter updated Email: "+ColorConstants.RESET);
			gymOwner.setEmail(email);
		}
		System.out.println(ColorConstants.YELLOW+"Would you like to edit your Password? Yes/No"+ColorConstants.RESET);
		choice = sc.next();
		if(choice.equals("Yes")){
			System.out.print(ColorConstants.YELLOW+"Enter updated Password: "+ColorConstants.RESET);
			gymOwner.setPassword(in.next());
		}
		System.out.println(ColorConstants.YELLOW+"Would you like to edit your Name? Yes/No"+ColorConstants.RESET);
		choice = sc.next();
		gymOwner.setRoleId("GymOwner");
		if(choice.equals("Yes")){
			System.out.print(ColorConstants.YELLOW+"Enter updated Name: "+ColorConstants.RESET);
			gymOwner.setName(in.next());
		}
		System.out.println(ColorConstants.YELLOW+"Would you like to edit your Phone Number? Yes/No"+ColorConstants.RESET);
		choice = sc.next();
		if(choice.equals("Yes")) {
			System.out.print( ColorConstants.YELLOW+"Enter updated Phone Number: "+ColorConstants.RESET);
			gymOwner.setPhoneNumber(in.next());
		}
		System.out.println(ColorConstants.YELLOW+"Would you like to edit your PAN Card Number? Yes/No"+ColorConstants.RESET);
		choice = sc.next();
		if(choice.equals("Yes")) {
			System.out.print(ColorConstants.YELLOW+"Enter updated PAN Number: "+ColorConstants.RESET);
			gymOwner.setPanNumber(in.next());
		}
		System.out.println( ColorConstants.YELLOW+"Would you like to edit your Aadhaar Card Number? Yes/No"+ColorConstants.RESET);
		choice = sc.next();
		if(choice.equals("Yes")) {
			System.out.print(ColorConstants.YELLOW+"Enter updated Aadhaar Card Number: "+ColorConstants.RESET);
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
		System.out.println("----------------------------------------------------------------" );
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		System.out.printf(ColorConstants.BLUE+"%15s%15s%15s%20s", "Gym Owner Name", "Phone Number", "PAN Number", "Aadhaar Number"+ColorConstants.RESET);
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println("----------------------------------------------------------------" );

		System.out.println();
		System.out.printf(ColorConstants.BLUE+"%15s%15s%15s%20s", gymOwner.getName(), gymOwner.getPhoneNumber(), gymOwner.getPanNumber(),
				gymOwner.getAadharNumber());
		System.out.println();
		System.out.println("\n-----------------------------------------------------------------------------------"+ColorConstants.RESET);
	}

	public void addGym(Scanner in, String email) {
		System.out.println(ColorConstants.YELLOW+"Please the details of the gym you want to add: "+ColorConstants.RESET);

		Gym gym = new Gym();
		gym.setGymId(IdGenerator.generateId("Gym"));
		System.out.print(ColorConstants.YELLOW+"Enter name of the Gym: "+ColorConstants.RESET);
		gym.setGymName(in.next());
		gym.setOwnerEmail(email);
		System.out.print(ColorConstants.YELLOW+"Enter address of the gym: "+ColorConstants.RESET);
		gym.setAddress(in.next());
		System.out.print(ColorConstants.YELLOW+"Enter number of slots available: "+ColorConstants.RESET);
		try {
			gym.setSlotCount(in.nextInt());
		} catch (InputMismatchException e) {
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
			return;
		}
		System.out.print(ColorConstants.YELLOW+"Enter seats available per slot: "+ColorConstants.RESET);
		gym.setSeatsPerSlotCount(in.nextInt());
		gym.setVerified(false);

		gymOwnerBusiness.addGym(gym);
	}

	public void editGym(Scanner in, String email) throws GymNotFoundException {
		System.out.println(ColorConstants.YELLOW+"Please Enter Gym Details: "+ColorConstants.RESET);
		Scanner sc = new Scanner(System.in);
		Gym gym = new Gym();

		String choice;

		System.out.println(ColorConstants.YELLOW+"Enter Gym Id: "+ColorConstants.RESET);
		gym.setGymId(in.next());
		Gym existingGym = gymOwnerBusiness.getGymById(gym.getGymId());

		// Check if gym exists and populate values if it exists
		if (existingGym != null) {
			// Use existing values as default values
			gym.setGymName(existingGym.getGymName());
			gym.setAddress(existingGym.getAddress());
			gym.setSlotCount(existingGym.getSlotCount());
			gym.setSeatsPerSlotCount(existingGym.getSeatsPerSlotCount());
			gym.setGymId(existingGym.getGymId());
			gym.setOwnerEmail(existingGym.getOwnerEmail());
		}
		System.out.println(ColorConstants.YELLOW+"Would you like to edit the name of your gym? Yes/No"+ColorConstants.RESET);
		choice = sc.next();
		if(choice.equals("Yes")){
			System.out.println(ColorConstants.YELLOW+"Enter updated name of the gym: "+ColorConstants.RESET);
			gym.setGymName(in.next());
			gym.setOwnerEmail(email);
		}
		System.out.println(ColorConstants.YELLOW+"Would you like to edit the Address of your gym? Yes/No"+ColorConstants.RESET);
		choice = sc.next();
		if(choice.equals("Yes")){
			System.out.println(ColorConstants.YELLOW+"Enter updated address of the gym: "+ColorConstants.RESET);
			gym.setAddress(in.next());
		}
//
		System.out.println(ColorConstants.YELLOW+"Would you like to edit the number of slots available? Yes/No"+ColorConstants.RESET);
		choice = sc.next();
		if(choice.equals("Yes")) {
			System.out.println(ColorConstants.YELLOW+"Enter updated number of slots: "+ColorConstants.RESET);
			try {
				gym.setSlotCount(in.nextInt());
				System.out.println(ColorConstants.YELLOW+"Would you like to edit the number of seats in each slot? Yes/No"+ColorConstants.RESET);
				choice = sc.next();
				if (choice.equals("Yes")) {
					System.out.print(ColorConstants.YELLOW+"Enter updated number of seats per slot: "+ColorConstants.RESET);
					gym.setSeatsPerSlotCount(in.nextInt());
				}
			} catch (InputMismatchException e) {
				System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
				return;
			}
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
			System.out.println(ColorConstants.RED + "Sorry! Unable to find gyms!" + ColorConstants.RESET);
			return;
		}
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		System.out.printf(ColorConstants.BLUE+"%15s%15s%15s%15s%15s%15s", "Gym Id", "Gym Name", "Address", "SlotCount", "SeatsPerSlot", "Verification"+ColorConstants.RESET);
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		gymDetails.forEach(gym -> {
			System.out.println();
			System.out.printf(ColorConstants.CYAN+"%15s%15s%15s%15s%15s%15s", gym.getGymId(), gym.getGymName(), gym.getAddress(),
					gym.getSlotCount(), gym.getSeatsPerSlotCount(),
					gym.isVerified() ? ColorConstants.GREEN+"Verified" : ColorConstants.RED+"Not Verified");
		});
		System.out.println();
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
	}

	public void addSlot(Scanner in, String email) {
		System.out.println(ColorConstants.YELLOW+"Enter details of the slot: "+ColorConstants.RESET);
		Slot slot = new Slot();
		slot.setSlotId(IdGenerator.generateId("Slot"));
		System.out.print( ColorConstants.YELLOW+"Enter Gym Id:"+ColorConstants.RESET);
		slot.setGymId(in.next());
		System.out.print(ColorConstants.YELLOW+"Enter Slot Date: "+ColorConstants.RESET);
		slot.setDate(in.next());
		System.out.print(ColorConstants.YELLOW+"Enter Slot Start Time: "+ColorConstants.RESET);
		slot.setStartTime(in.next());
		System.out.print(ColorConstants.YELLOW+"Enter Slot End Time: "+ColorConstants.RESET);
		slot.setEndTime(in.next());
		System.out.print(ColorConstants.YELLOW+"Enter number of seats in slot: "+ColorConstants.RESET);
		try {
			slot.setNumOfSeats(in.nextInt());
		} catch (InputMismatchException e) {
			System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
			return;
		}
		System.out.print(ColorConstants.YELLOW+"Enter Trainer: "+ColorConstants.RESET);
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

	public void gymOwnerMenu(Scanner in, String email) throws GymNotFoundException {
		boolean recur = true;
		while (recur) {
			System.out.println("----------------------------------------------------------------" );
			System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
			System.out.println(ColorConstants.BLUE+"\n                                     Actions:                                          "+ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
			System.out.println( "----------------------------------------------------------------" );

			System.out.println(ColorConstants.YELLOW+"1. View Profile"+ColorConstants.RESET);
			System.out.println( ColorConstants.YELLOW+"2. Edit Profile"+ColorConstants.RESET);
			System.out.println( ColorConstants.YELLOW+"3. Add Gym"+ColorConstants.RESET);
			System.out.println( ColorConstants.YELLOW+"4. Edit Gym"+ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW+"5. Add Slot"+ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW+"6. View All Gym Details"+ColorConstants.RESET);
			System.out.println( ColorConstants.YELLOW+"7. LogOut\n"+ColorConstants.RESET);

			System.out.print( ColorConstants.YELLOW+"Please enter your desired choice: ");
			int choice = in.nextInt();


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
					System.out.println(ColorConstants.GREEN + "You've been successfully logged out!" + ColorConstants.RESET);
			}
		}

	}

}