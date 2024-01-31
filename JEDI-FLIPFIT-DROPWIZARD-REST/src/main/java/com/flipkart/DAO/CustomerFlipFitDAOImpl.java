package com.flipkart.DAO;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.constants.SQLConstants;
import com.flipkart.exception.SlotNotFoundException;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFlipFitDAOImpl implements CustomerFlipFitDAO {

	// returns the list of all the gyms
	public List<Gym> fetchGymList(String city) {
		Connection connection = null;
		List<Gym> gyms = new ArrayList<Gym>();
		try {connection = DBUtils.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement statement = connection.prepareStatement(SQLConstants.SQL_SELECT_ALL_GYMS_BY_ADDRESS);
			statement.setString(1, city);
			//System.out.println(statement);
			// Step 3: Execute the query or update query
			ResultSet rs = statement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				Gym gym = new Gym();
				gym.setGymId(rs.getString("gymId"));
				gym.setGymName(rs.getString("gymName"));
				gym.setOwnerEmail(rs.getString("ownerEmail"));
				gym.setAddress(rs.getString("address"));
				gym.setSlotCount(rs.getInt("slotCount"));
				gym.setSeatsPerSlotCount(rs.getInt("seatsPerSlotCount"));
				gym.setVerified(rs.getBoolean("isVerified"));
				gyms.add(gym);
//                    System.out.println(id + "," + name + "," + email + "," + country + "," + password);
			}
		} catch (SQLException e) {
//            printSQLException(e);
		}
		return gyms;
	}

	public Customer getCustomerDetails(String customerEmailId) {
		Connection connection = null;
		Customer customer = new Customer();
		try {
			connection = DBUtils.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_VIEW_CUSTOMER);
			preparedStatement.setString(1, customerEmailId);
			//System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next())
				return null;
			// Step 4: Process the ResultSet object.
			do {
				customer.setEmail(rs.getString("email"));
				customer.setName(rs.getString("name"));
				customer.setPhoneNumber(rs.getString("phoneNum"));
				customer.setAge(rs.getInt("age"));
				customer.setAddress(rs.getString("address"));

//				customer.setAadharNumber(rs.getString("aadharNum"));
//				gymOwner.setPanNumber(rs.getString("panNum"));

				// System.out.println(id + "," + name + "," + email + "," + country + "," + password);
			} while (rs.next());
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
		return customer;
	}


	// shows all the slots in the provided gym
	public List<Slot> fetchSlotList(String gymId) throws SlotNotFoundException {
		Connection connection = null;
		List<Slot> slots=new ArrayList<>();
		try {connection = DBUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQLConstants.SQL_FETCH_SLOT_LIST);
			//System.out.println(statement);
			statement.setString(1, gymId);
			//executing the query
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				Slot s=new Slot();
				s.setSlotId(rs.getString("slotId"));
				s.setTrainer(rs.getString("trainer"));
				s.setGymId(rs.getString("gymId"));
				s.setNumOfSeatsBooked(rs.getInt("numOfSeatsBooked"));
				s.setNumOfSeats(rs.getInt("numOfSeats"));
				s.setStartTime(rs.getString("startTime"));
				s.setEndTime(rs.getString("endTime"));
				slots.add(s);
			}

		} catch (SQLException sqlExcep) {
			//printSQLException(sqlExcep);
		}
		return slots;
	}
	// shows all the bookings of the slots made by the customer
	public List<Booking> fetchBookedSlots(String email) {
		Connection connection = null;
		List<Booking> bookings=new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQLConstants.SQL_SELECT_BOOKED_SLOTS_BY_CUSTOMER);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Booking b=new Booking();
				b.setBookingId(rs.getString("bookingId"));
				b.setCustomerEmail(rs.getString("customerEmail"));
				b.setSlotId(rs.getString("slotId"));
				b.setGymId(rs.getString("gymId"));
				b.setType(rs.getString("type"));
				b.setDate(rs.getString("date"));
				bookings.add(b);
			}
		} catch (SQLException sqlExcep) {
            printSQLException(sqlExcep);
		}
		return bookings;
	}

	// books the slot with the given slotId for the customer
	public void bookSlots(String bookingId, String slotId, String gymId, String type, String date, String customerEmail) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQLConstants.SQL_INSERT_BOOKING);
			statement.setString(1, bookingId);
			statement.setString(2, slotId);
			statement.setString(3, gymId);
			statement.setString(4, type);
			statement.setString(5, date);
			statement.setString(6, customerEmail);
			statement.executeUpdate();
		} catch (SQLException sqlExcep) {
//            printSQLException(sqlExcep);
		}
	}
	public Customer getProfile(Customer customer)
	{
		return customer;
	}
	// returns true if the slot is fully booked
	public boolean isFull(String slotId, String date) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_CHECK_FULL_SLOT);
			preparedStatement.setString(1, slotId);
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
//            printSQLException(e);
		}
		return false;
	}

	public String getBookingId(String email,String slotId){
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_BOOKING_ID);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, slotId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				return rs.getString("bookingId");
			}
		} catch (SQLException e) {
//            printSQLException(e);
		}
		return "";
	}

	// checks if the slot is already booked by the customer
	public boolean alreadyBooked(String slotId, String email, String date) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_CHECK_ALREADY_BOOKED);
			preparedStatement.setString(1, slotId);
			preparedStatement.setString(2, email);
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
//            printSQLException(e);
		}
		return false;
	}

	public int getNumberOfSeatsBooked(String slotId)
	{
		Connection connection = null;

		try {
			connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_GET_NUMBER_OF_BOOKED_SEATS);
			preparedStatement.setString(1, slotId);
			ResultSet rs =  preparedStatement.executeQuery();
			while (rs.next()) {
				return rs.getInt("numOfSeatsBooked");
			}

		}
		catch (SQLException sqlExcep) {
//            printSQLException(sqlExcep);
		}
		return 0;
	}
	public int getNumberOfSeats(String slotId)
	{
		Connection connection = null;

		try {
			connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_GET_NUMBER_OF_SEATS);
			preparedStatement.setString(1, slotId);
			ResultSet rs =  preparedStatement.executeQuery();
			while (rs.next()) {
				return rs.getInt("numOfSeats");
			}

		}
		catch (SQLException sqlExcep) {
//            printSQLException(sqlExcep);
		}
		return 0;
	}
	public boolean updateNumOfSeats(String slotId, int seats)
	{
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_UPDATE_NUMBER_OF_BOOKED_SEATS);
			preparedStatement.setString(2, slotId);
			preparedStatement.setInt(1, seats);
			preparedStatement.executeUpdate();
			return true;
		}
		catch (SQLException sqlExcep) {
//            printSQLException(sqlExcep);
		}
		return false;
	}
	// cancels the booking of the customer made earlier
	public boolean cancelBooking(String bookingId, String email) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQLConstants.SQL_DELETE_BOOKING);
			statement.setString(2, bookingId);
			statement.setString(1, email);
			statement.executeUpdate();
			return true;
		} catch (SQLException sqlExcep) {
//            printSQLException(sqlExcep);
		}
		return false;
	}

	// checks if the slot exists or not
	public boolean checkSlotExists(String slotId, String gymId) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_CHECK_SLOT_EXISTS);
			preparedStatement.setString(1, slotId);
			preparedStatement.setString(2, gymId);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
//            printSQLException(e);
		}
		return false;
	}

	// checks if the gym is approved
	public boolean checkGymApprove(String gymId) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_CHECK_GYM_APPROVAL);
			preparedStatement.setString(1, gymId);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
//            printSQLException(e);
		}
		return false;
	}

	public int editCustomerDetails(Customer customer) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_UPDATE_USER);
			preparedStatement.setString(1, customer.getEmail());
			preparedStatement.setString(2, customer.getPassword());
			preparedStatement.setString(3, "Customer");
			preparedStatement.setString(4, customer.getEmail());
			// Step 3: Execute the query or update query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// print SQL exception information
//            printSQLException(e);
		}
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_UPDATE_CUSTOMER);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getPhoneNumber());
			preparedStatement.setInt(3,customer.getAge());
			preparedStatement.setString(4, customer.getAddress());
			preparedStatement.setString(5, customer.getEmail());
			// Step 3: Execute the query or update query
			return  preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// print SQL exception information
//            printSQLException(e);
		}

		System.out.println(SQLConstants.SQL_UPDATE_CUSTOMER);
		// Step 1: Establishing a Connection
		return 0;
	}
	// prints the SQL Exception
	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}