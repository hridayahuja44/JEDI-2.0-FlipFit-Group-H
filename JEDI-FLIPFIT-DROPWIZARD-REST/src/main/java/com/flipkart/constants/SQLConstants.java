/**
 * 
 */
package com.flipkart.constants;

/**
 * 
 */
public class SQLConstants {
	public static final String SQL_FETCH_SLOT_LIST = "Select * From Slot Where gymId=?;";
	public static final String SQL_SELECT_USER_LOGIN_CREDENTIAL = "select email, password, role from user where email = ?;";
	public static final String SQL_SELECT_GYM_OWNER_DETAILS = "select email, name, phoneNum, aadharNum, panNum from gymOwner where email = ?;";
	public static final String SQL_INSERT_USER = "INSERT INTO user (email, password, role) VALUES (?, ?, ?);";
	public static final String SQL_UPDATE_USER = "update user set email = ?, password = ?, role = ?" + " where email = ?;";

	public static final String SQL_VIEW_CUSTOMER = "SELECT * From Customer where email= ?;";
	public static final String SQL_UPDATE_CUSTOMER="UPDATE customer set name=? ,phoneNum=? ,age=? ,address=? where email=?;";
	public static final String SQL_INSERT_GYM_OWNER = "INSERT INTO gymOwner (email, name, phoneNum, aadharNum, panNum, isVerified) VALUES (?, ?, ?, ?, ?, ?);";
	public static final String SQL_UPDATE_GYM_OWNER = "UPDATE gymOwner set email = ?, name = ?, phoneNum = ?, aadharNum = ?, panNum = ?, isVerified = ? where email = ?;";

	public static final String SQL_READ_GYM = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from gym where gymId = ?;";
	public static final String SQL_INSERT_GYM = "INSERT INTO gym"
			+ "  (gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?);";
	public static final String SQL_UPDATE_GYM ="update gym"
			+ "  set gymId = ?, gymName = ?, ownerEmail = ?, address = ?, slotCount = ?, seatsPerSlotCount = ?, isVerified = ? where gymId = ?;";

	public static final String SQL_READ_SLOT_FROM_GYMID = "select slotId, gymId, startTime, endTime, trainer, numOfSeats, numOfSeatsBooked from slot where gymId =  ?;";
	public static final String SQL_INSERT_SLOT = "INSERT INTO slot" + "  (slotId, gymId, date, startTime, endTime, trainer, numOfSeats, numOfSeatsBooked) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?);";

	public static final String SQL_SELECT_ALL_GYMS = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from gym where ownerEmail = ?;";
	public static final String SQL_SELECT_GYM_WITH_OWNER = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from gym where gymId = ? and ownerEmail = ?;";
	public static final String SQL_SELECT_GYM_BY_ID = "SELECT gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified FROM gym WHERE gymId = ?";
	public static final String SQL_VIEW_ALL_GYMS = "select * from gym;";
	public static final String SQL_SELECT_ALL_GYMS_BY_ADDRESS = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from gym where address=? and isVerified=1;";
	public static final String SQL_SELECT_ALL_GYMS_WITHOUT_FILTER = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from gym;";
	public static final String SQL_SELECT_SLOTS_BY_GYM_ID = "Select * From slot Where gymId=?;";
	public static final String SQL_SELECT_BOOKINGS_BY_CUSTOMER_EMAIL = "Select * From booking where customerEmail = ?;";
	public static final String SQL_SELECT_FULL_SLOTS = "Select * from slot where slotId=? and (numOfSeatsBooked>=numOfSeats);";
	//public static final String SQL_SELECT_VERIFICATION_STATUS_BOOKING = "select isVerified from Booking where slotId=? and customerEmail =?;";
	public static final String SQL_SELECT_VERIFICATION_STATUS_SLOT = "select isVerified from slot where slotId=? and gymId =  ?;";
	public static final String SQL_SELECT_VERIFICATION_STATUS_GYM = "select isVerified from gym where gymId =  ?;";
	public static final String SQL_SELECT_VERIFICATION_STATUS_GYM_OWNER = "select isVerified from gym where gymId =  ?;";

	public static final String SQL_SELECT_ALL_GYM_OWNERS = "select email, name, phoneNum, aadharNum, panNum, isVerified from gymOwner;";
	public static final String SQL_SELECT_PENDING_GYM_OWNERS = "select email, name, phoneNum, aadharNum, panNum, isVerified from gymOwner where isVerified = ?;";
	public static final String SQL_SELECT_PENDING_GYMS = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from gym where isVerified = ?;";
	public static final String SQL_APPROVE_GYM_OWNER_BY_ID = "update gymOwner set isVerified=1 WHERE email=?;";
	public static final String SQL_APPROVE_ALL_GYMS_OWNERS = "update gymOwner set isVerified=1 WHERE isVerified=0;";
	public static final String SQL_APPROVE_GYM_BY_ID = "update gym set isVerified=1 where gymId = ?;";
	public static final String SQL_APPROVE_ALL_GYMS = "update gym set isVerified=1 WHERE isVerified=0;";

	public static final String SQL_SELECT_SLOTS_BY_GYM = "Select * From slot Where gymId=?;";
	public static final String SQL_SELECT_BOOKED_SLOTS_BY_CUSTOMER = "Select * From booking WHERE customerEmail=?;";
	public static final String SQL_INSERT_BOOKING = "INSERT INTO booking (bookingId,slotId,gymId,type,date,customerEmail) values(?, ?, ?, ?, ?, ?);";
	public static final String SQL_CHECK_FULL_SLOT = "Select * from slot where slotId=? and (numOfSeatsBooked>=numOfSeats);";

	public static final String SQL_SELECT_BOOKING_ID = "Select * from booking where customerEmail=? and slotId=?;";
	public static final String SQL_CHECK_ALREADY_BOOKED = "select type from booking where slotId=? and customerEmail = ?;";
	public static final String SQL_DELETE_BOOKING = "Delete from booking where customerEmail = ? and bookingId = ?;";
	public static final String SQL_CHECK_SLOT_EXISTS = "select isVerified from slot where slotId=? and gymId = ?;";
	public static final String SQL_CHECK_GYM_APPROVAL = "select isVerified from gym where gymId = ?;";
	public static final String SQL_INSERT_CUSTOMER = "INSERT INTO customer (email, name, phoneNum, age, address) VALUES (?, ?, ?, ?, ?);";
	public static final String SQL_GET_NUMBER_OF_BOOKED_SEATS = "select numOfSeatsBooked from slot where slotId=?;";
	public static final String SQL_UPDATE_NUMBER_OF_BOOKED_SEATS = "UPDATE slot set numOfSeatsBooked=? where slotId=?;";
	public static final String SQL_GET_NUMBER_OF_SEATS = "select numOfSeats from slot where slotId=?;";

}
