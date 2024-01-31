package com.flipkart.DAO;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.exception.SlotNotFoundException;

import java.util.List;

public interface CustomerFlipFitDAO {
	public Customer getProfile(Customer customer);

	public int editCustomerDetails(Customer customer);
	public List<Gym> fetchGymList(String city);
	public Customer getCustomerDetails(String customerEmailId);

	public List<Slot> fetchSlotList(String gymId) throws SlotNotFoundException;

	public List<Booking> fetchBookedSlots(String email);

	public void bookSlots(String bookingId, String slotId, String gymId, String type, String date, String customerEmail);

	public boolean isFull(String slotId, String date);

	public boolean alreadyBooked(String slotId, String email, String date);

	public boolean cancelBooking(String bookingId, String email);

	public boolean checkSlotExists(String slotId, String gymId);

	public boolean checkGymApprove(String gymId);

	public int getNumberOfSeatsBooked(String slotId);

	public int getNumberOfSeats(String slotId);
}