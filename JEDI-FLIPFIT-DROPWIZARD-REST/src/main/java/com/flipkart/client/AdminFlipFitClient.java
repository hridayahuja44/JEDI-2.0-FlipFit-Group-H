
package com.flipkart.client;

import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.constants.ColorConstants;
import com.flipkart.service.*;

import java.util.*;

/**
 *
 */

public class AdminFlipFitClient {

	AdminFlipFitServiceImpl adminBusiness = new AdminFlipFitServiceImpl();
	List<GymOwner> gymOwnerList = adminBusiness.getGymOwners();
	List<Gym> gymList = adminBusiness.getGym();
	Scanner sc = new Scanner(System.in);

	public void viewAllPendingGymOwnerRequests() {

		viewAllGymOwners(adminBusiness.viewAllPendingGymOwnerRequests());
	}
	public void viewAllPendingGymRequests() {

		viewAllGyms(adminBusiness.viewAllPendingGymRequests());
	}

	public void approveSingleGymOwnerRequest() {
		System.out.print(ColorConstants.YELLOW+"Enter the email address you want to approve: "+ColorConstants.RESET);
		adminBusiness.approveSingleGymOwnerRequest(sc.next());
	}

	public void approveSingleGymRequest() {
		System.out.print(ColorConstants.YELLOW+"Enter the ID of gym you want to approve: "+ColorConstants.RESET);
		adminBusiness.approveSingleGymRequest(sc.next());
	}

	public void approvePendingGymOwnerRequests() {
		adminBusiness.approveAllPendingGymOwnerRequests();
		System.out.println(ColorConstants.YELLOW+"Successful approval of all pending gym owner requests"+ColorConstants.RESET);
	}

	public void approvePendingGymRequests() {
		adminBusiness.approveAllPendingGymRequests();
		System.out.println(ColorConstants.GREEN+"Successful approval of all pending gym requests"+ColorConstants.RESET);
	}

	public void adminMenu(Scanner in) throws Exception {
		System.out.println("----------------------------------------------------------------" );
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println(ColorConstants.BLUE + "                 FLIPFIT - SYSTEM ADMIN MENU                     " + ColorConstants.RESET);
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println("----------------------------------------------------------------" );

		while (true) {
			System.out.println(ColorConstants.YELLOW+"1. View All The Gyms \n 2. View All The Gym Owners \n 3. View All The Pending Gym Owner Queued Requests \n 4. View All The Pending Gym Queued Requests \n 5. Approve Rest All Of The Pending Gym Owner Requests \n 6. Approve All Of The Pending Gym Requests \n 7. Approve Single Gym Owner Request \n 8. Approve Single Gym Request \n 9. Exit"+ColorConstants.RESET);

			System.out.print("Please enter your desired choice: ");
			int choice = in.nextInt();
			switch (choice) {
				// Case statements
				case 1:
					viewAllGyms(gymList);
					break;
				case 2:
					viewAllGymOwners(gymOwnerList);
					break;
				case 3:
					viewAllPendingGymOwnerRequests();
					break;
				case 4:
					viewAllPendingGymRequests();
					break;
				case 5:
					approvePendingGymOwnerRequests();
					break;
				case 6:
					approvePendingGymRequests();
					break;
				case 7:
					approveSingleGymOwnerRequest();
					break;
				case 8:
					approveSingleGymRequest();
					break;
				case 9:
					System.out.println(ColorConstants.GREEN+"System Admin logged out!"+ColorConstants.RESET);
					return;
				// Default case statement
				default:
					System.out.println(ColorConstants.RED+"You've entered an invalid option. " +
							ColorConstants.YELLOW+"Enter a valid option."+ColorConstants.RESET);
			}
		}
	}

	public void viewAllGyms(List<Gym> gyms) {
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println(ColorConstants.BLUE + "                                        GYM LIST                                           " + ColorConstants.RESET);
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println( "----------------------------------------------------------------------------------------------" );

		gyms.forEach(gym -> {
			System.out.printf(ColorConstants.YELLOW+"%15s%15s%15s%15s%15s%15s", "Gym ID", "Name", "Owner Email", "Address", "Slot Count", "Verification"+ ColorConstants.RESET);
			System.out.println();
			System.out.printf(ColorConstants.BLUE+"%15s%15s%15s%15s%15s%15s", gym.getGymId(), gym.getGymName(), gym.getOwnerEmail(), gym.getAddress(), gym.getSlotCount(), (gym.isVerified() ? ColorConstants.GREEN+"Approved" : ColorConstants.RED+"Pending")+ ColorConstants.RESET);
			System.out.println();
			System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------------------------------------" + ColorConstants.RESET);
		});
	}

	public void viewAllGymOwners(List<GymOwner> gymOwners) {
		System.out.println("----------------------------------------------------------------------------------------------" );
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println(ColorConstants.BLUE + "                                      GYM OWNERS LIST                                       " + ColorConstants.RESET);
		System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------------------------------------" + ColorConstants.RESET);
		System.out.println("----------------------------------------------------------------------------------------------" );

		gymOwners.forEach(gymOwner -> {
			System.out.println(ColorConstants.YELLOW+"Name: " + gymOwner.getName()+ ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW+"Phone Number: " + gymOwner.getPhoneNumber()+ ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW+"Aadhar Number: " + gymOwner.getAadharNumber()+ ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW+"PAN Number: " + gymOwner.getPanNumber()+ ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW+"Verification: " + (gymOwner.isVerified() ? ColorConstants.GREEN+"Approved" : ColorConstants.RED+"Pending")+ ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW + "----------------------------------------------------------------------------------------------"+ ColorConstants.RESET);
		});
	}
}