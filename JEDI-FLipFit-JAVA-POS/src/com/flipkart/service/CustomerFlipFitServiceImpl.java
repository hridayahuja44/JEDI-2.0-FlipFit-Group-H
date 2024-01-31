/**
 *
 */
package com.flipkart.service;

import com.flipkart.DAO.*;
import com.flipkart.bean.*;
import com.flipkart.constants.ColorConstants;
import com.flipkart.exception.CustomerNotFoundException;
import com.flipkart.exception.SlotNotFoundException;
import com.flipkart.utils.IdGenerator;

import java.util.*;

public class CustomerFlipFitServiceImpl implements CustomerFlipFitServiceInterface {

	CustomerFlipFitDAOImpl customerDAO = new CustomerFlipFitDAOImpl();
//	List<Customer> customers = new ArrayList<>();
//	List<Booking> bookings = new ArrayList<>();
//
//	List<Slot> slots = new ArrayList<>();
//	List<Gym> gyms = new ArrayList<>();
	//Date d1 = new Date();


	public Customer getProfile(String email) throws CustomerNotFoundException {
//		System.out.println(ColorConstants.GREEN+"Successfully fetched the customer profile"+ColorConstants.RESET);
//		Customer cus=	customerDAO.getProfile(customer);
//		if (cus == null)
//			throw new CustomerNotFoundException();
//		System.out.println(ColorConstants.GREEN +"Fetched Customer details successfully! "+ColorConstants.RESET);
//		return cus;
		Customer customers = customerDAO.getCustomerDetails(email);
		if (customers == null)
			throw new CustomerNotFoundException();
//		System.out.println(ColorConstants.GREEN +"Fetched Customer details successfully! " + email+ColorConstants.RESET);
		return customers;
	}


	/**
	 * Gives functionality of updating customer's personal data.
	 * @param customer the Customer object for which the profile data needs to be updated
	 */
	public void editProfile(Customer customer) throws CustomerNotFoundException{
		int updatedCount = customerDAO.editCustomerDetails(customer);
		if (updatedCount == 0)
			throw new CustomerNotFoundException();
		//System.out.println(ColorConstants.GREEN + "\nEdited your profile Successfully!" + ColorConstants.RESET);
	}




	/**
	 * Obtains all the bookings done by the given customer email.
	 * @param email the Customer email for which the bookings data are requested
	 * @return List of bookings done by the given customer email
	 */
	public List<Booking> getBookings(String email) {
		System.out.println(ColorConstants.GREEN+"Successfully fetched the list of bookings"+ColorConstants.RESET);
		List<Booking> bookings = customerDAO.fetchBookedSlots(email);
		for(Booking booking : bookings){
			System.out.println(booking.getBookingId());
		}
		return bookings;
	}
	/**
	 * Performs booking cancellation operation for the given customer email.
	 * @param bookingId the id of booking for which cancellation needs to be performed
	 * @param email the Customer email for which the booking cancellation is requested
	 * @return returns true of the booking gets cancelled successfully else returns false
	 */
	public boolean cancelBooking(String bookingId, String email)  {
		System.out.println(ColorConstants.GREEN+"Successfully cancelled the booking "+ColorConstants.RESET);
		return customerDAO.cancelBooking(bookingId, email);
	}
	/**
	 * Obtains all the gyms for the given city.
	 * @param city the city name for which the gym list is requested
	 * @return returns List of gyms available for the given city
	 */
	public List<Gym> getGymInCity(String city) {
		System.out.println(ColorConstants.GREEN+"\nSuccessfully fetched the gyms in city "+city + "\n"+ColorConstants.RESET);
		return customerDAO.fetchGymList(city);

	}
	/**
	 * Obtains all the slots for the given gymId.
	 * @param gymId the Gym Id for which the slot details are requested
	 * @return returns List of available slots for the given gymId
	 */
	public List<Slot> getSlotInGym(String gymId) throws SlotNotFoundException{

		List<Slot> slotsOfGym = customerDAO.fetchSlotList(gymId);
		if(slotsOfGym.isEmpty()) throw new SlotNotFoundException();
		return slotsOfGym;

	}
	/**
	 * Performs booking operation for the given customer email on the given date for the given slotId
	 * @param email the email of customer who requested the booking operation
	 * @param slotId the slot id in which the customer wants to book a seat
	 * @param date the date on which the customer wants to book a seat
	 * @return returns integer signal based on the customer's booking status
	 */
	public int bookSlot(String gymId, String slotId, String email, String date)
	{
		Integer bookedSeatsNum = customerDAO.getNumberOfSeatsBooked(slotId);
		Integer totalSeatsNum = customerDAO.getNumberOfSeats(slotId);
		if(customerDAO.alreadyBooked(slotId, email, date))
		{
			//System.out.println("entered already booked");
			String bookingId = customerDAO.getBookingId(email,slotId);
			customerDAO.cancelBooking(bookingId, email);
			customerDAO.updateNumOfSeats(slotId,bookedSeatsNum-1);
			if(bookedSeatsNum<totalSeatsNum)
			{
				customerDAO.updateNumOfSeats(slotId,bookedSeatsNum+1);
				customerDAO.bookSlots(IdGenerator.generateId("Booking"), slotId, gymId,"confirmed",date,email);
			}
			else
			{
				customerDAO.updateNumOfSeats(slotId,bookedSeatsNum+1);
				customerDAO.bookSlots(IdGenerator.generateId("Booking"), slotId, gymId,"waitlisted",date,email);
			}
			return 0;
		}
		if(customerDAO.isFull(slotId, date))
		{
			customerDAO.updateNumOfSeats(slotId,bookedSeatsNum+1);
			customerDAO.bookSlots(IdGenerator.generateId("Booking"), slotId, gymId,"waitlisted",date,email);
			return 1;
		}
		else
		{
			if(bookedSeatsNum<totalSeatsNum)
			{
				customerDAO.updateNumOfSeats(slotId,bookedSeatsNum+1);
				customerDAO.bookSlots(IdGenerator.generateId("Booking"), slotId, gymId,"confirmed",date,email);
			}
			else
			{
				customerDAO.updateNumOfSeats(slotId,bookedSeatsNum+1);
				customerDAO.bookSlots(IdGenerator.generateId("Booking"), slotId, gymId,"waitlisted",date,email);
			}
			return 2;
		}

	}

	/**
	 * Checks if the slot is already booked or not
	 * @param slotId the slot id for which the booking status is requested
	 * @param date the date on which the booking status is requested
	 * @return returns true if the slot id for the given date is fully booked else returns false
	 */
	public boolean isSlotBooked(String slotId, String date) {
		return customerDAO.isFull(slotId, date);
	}
	/**
	 * Checks if the customer has already booked a seat in the same slot for the given date
	 * @param slotId the slot id for which the booking status is requested
	 * @param date the date on which the booking status is requested
	 * @param customerEmail the email of customer for which the booking status is getting checked
	 * @return returns true if the customer has already booked a seat on the same date in the same slot
	 */
	public boolean hasBookedSlotAlready(String slotId, String customerEmail, String date) {
		return customerDAO.alreadyBooked(slotId, customerEmail, date);
	}

	/**
	 * Checks if a gym is approved for a specified gym ID.
	 * @param gymId The ID of the gym
	 * @return true if the gym is approved, false otherwise
	 */
	public boolean checkGymApprove(String gymId) {
		return customerDAO.checkGymApprove(gymId);
	}

}