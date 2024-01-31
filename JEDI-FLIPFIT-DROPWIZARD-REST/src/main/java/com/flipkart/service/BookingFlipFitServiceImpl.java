/**
 *
 */
package com.flipkart.service;


import com.flipkart.bean.Booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 */public class BookingFlipFitServiceImpl implements BookingFlipFitServiceInterface {

	List<Booking> bookings=new ArrayList<>();
	Date d1=new Date(); //current date

	public BookingFlipFitServiceImpl()
	{

	}
	/**
	 * Checks if a booking is confirmed or not for the given bookingId
	 * @param bookingId the id of a booking that needs to be checked
	 * @return true if the bookingId is confirmed else returns false
	 */
	public boolean isConfirmed(String bookingId) {

//		for(Booking b:bookings)
//		{
//			if(b.getBookingId().equals(bookingId))
//			{
//				if(b.getType()=="confirmed")
//					return true;
//				else
//					return false;
//			}
//		}
//		return false;

		return bookings.stream()
				.filter(b -> b.getBookingId().equals(bookingId))
				.anyMatch(b -> "confirmed".equals(b.getType()));
	}
	/**
	 * Gives a size of wait listed customers.
	 */
	public int getWaitingList() {
		return -1;
	}

}