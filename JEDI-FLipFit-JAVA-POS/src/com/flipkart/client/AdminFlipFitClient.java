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
		System.out.print(ColorConstants.CYAN + "Enter the email ID of the Gym Owner you would like to approve: " + ColorConstants.RESET);
		adminBusiness.approveSingleGymOwnerRequest(sc.next());
	}

	public void approveSingleGymRequest() {
		System.out.print(ColorConstants.CYAN + "Enter the Gym ID you would like to approve: " + ColorConstants.RESET);
		adminBusiness.approveSingleGymRequest(sc.next());
	}

	public void approvePendingGymOwnerRequests() {
		adminBusiness.approveAllPendingGymOwnerRequests();
		System.out.println(ColorConstants.GREEN + "All pending gym owner requests have been successfully approved." + ColorConstants.RESET);
	}

	public void approvePendingGymRequests() {
		adminBusiness.approveAllPendingGymRequests();
		System.out.println(ColorConstants.GREEN + "All pending gym requests have been successfully approved." + ColorConstants.RESET);
	}

	public void adminMenu(Scanner in) throws Exception {
//		System.out.println("");
		System.out.println(ColorConstants.BLUE + "--------------------- Admin FlipFit ---------------------" + ColorConstants.RESET);
		while (true) {
			System.out.println(ColorConstants.CYAN + "1. View all Gyms" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "2. View all Gym Owners" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "3. View all pending Gym Owner Requests" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "4. View all pending Gym Requests" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "5. Approve all pending Gym Owner Requests" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "6. Approve all pending Gym Requests" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "7. Approve Single Gym Owner Request" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "8. Approve Single Gym Request" + ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN + "9. Exit" + ColorConstants.RESET);

			System.out.print("Please enter your choice: " );
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
					System.out.println(ColorConstants.GREEN + "You've been logged out successfully!" + ColorConstants.RESET);
					return;
				// Default case statement
				default:
					System.out.println(ColorConstants.RED + "You've been an invalid choice!" + ColorConstants.RESET);
			}
		}
	}

	public void viewAllGyms(List<Gym> gyms) {
//		System.out.println(ColorConstants.BLUE + "--------------------- All Gyms ---------------------" + ColorConstants.RESET);
//		for (Gym gym : gyms) {
//			System.out.println(ColorConstants.CYAN + "Gym ID: " + gym.getGymId() + ColorConstants.RESET);
//			System.out.println(ColorConstants.CYAN + "Name: " + gym.getGymName() + ColorConstants.RESET);
//			System.out.println(ColorConstants.CYAN + "Owner Email ID: " + gym.getOwnerEmail() + ColorConstants.RESET);
//			System.out.println(ColorConstants.CYAN + "Address: " + gym.getAddress() + ColorConstants.RESET);
//			System.out.println(ColorConstants.CYAN + "Slot Count: " + gym.getSlotCount() + ColorConstants.RESET);
//			System.out.println(ColorConstants.CYAN + "Verification: " + (gym.isVerified() ? "Approved" : "Pending") + ColorConstants.RESET);
//			System.out.println(ColorConstants.BLUE +"-------------------------------------------------" + ColorConstants.RESET);
//		}

		System.out.println("========================================== All Gyms ==========================================");
		gyms.forEach(gym -> {
			System.out.printf("%15s%15s%15s%15s%15s%15s", "Gym ID", "Name", "Owner Email", "Address", "Slot Count", "Verification");
			System.out.println();
			System.out.printf("%15s%15s%15s%15s%15s%15s", gym.getGymId(), gym.getGymName(), gym.getOwnerEmail(), gym.getAddress(), gym.getSlotCount(), (gym.isVerified() ? "Approved" : "Pending"));
			System.out.println();
			System.out.println("==============================================================================================");
		});
	}



	public void viewAllGymOwners(List<GymOwner> gymOwners) {

		System.out.println("================= All Gym Owners =================");
		gymOwners.forEach(gymOwner -> {
			System.out.println(ColorConstants.CYAN+"Name: " + gymOwner.getName()+ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN+"Phone Number: " + gymOwner.getPhoneNumber()+ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN+"Aadhar Number: " + gymOwner.getAadharNumber()+ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN+"PAN Number: " + gymOwner.getPanNumber()+ColorConstants.RESET);
			System.out.println(ColorConstants.CYAN+"Verification: " + (gymOwner.isVerified() ? "Approved" : "Pending")+ColorConstants.RESET);
			System.out.println("===================================================");
		});
//		System.out.println(ColorConstants.BLUE + "--------------------- All Gym Owners ---------------------" + ColorConstants.RESET);
//		for (GymOwner gymOwner : gymOwners) {
//			System.out.println(ColorConstants.CYAN + "Name: " + gymOwner.getName() + ColorConstants.RESET);
//			System.out.println(ColorConstants.CYAN + "Phone Number: " + gymOwner.getPhoneNumber() + ColorConstants.RESET);
//			System.out.println(ColorConstants.CYAN + "Aadhaar Card Number: " + gymOwner.getAadharNumber() + ColorConstants.RESET);
//			System.out.println(ColorConstants.CYAN + "PAN Card Number: " + gymOwner.getPanNumber() + ColorConstants.RESET);
//			System.out.println(ColorConstants.CYAN + "Verification: " + (gymOwner.isVerified() ? "Approved" : "Pending") + ColorConstants.RESET);
//			System.out.println(ColorConstants.BLUE + "----------------------------------------------------" + ColorConstants.RESET);
//		}
	}
}
