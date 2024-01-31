package com.flipkart.DAO;

import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.constants.SQLConstants;
import com.flipkart.exception.UnauthorizedAccessException;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GymOwnerFlipFitDAOImpl implements GymOwnerFlipFitDAO {

	/**
	 * Retrieves gym owner details from the database
	 * @param gymOwnerEmailId The Email Id of the gym owner
	 * @return GymOwner object
	 */
	public GymOwner getGymOwnerDetails(String gymOwnerEmailId) {
		Connection connection = null;
		GymOwner gymOwner = new GymOwner();
		try {
			connection = DBUtils.getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_GYM_OWNER_DETAILS);
			preparedStatement.setString(1, gymOwnerEmailId);
			//System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next())
				return null;
			// Step 4: Process the ResultSet object.
			do {
				gymOwner.setEmail(rs.getString("email"));
				gymOwner.setName(rs.getString("name"));
				gymOwner.setPhoneNumber(rs.getString("phoneNum"));
				gymOwner.setAadharNumber(rs.getString("aadharNum"));
				gymOwner.setPanNumber(rs.getString("panNum"));

				// System.out.println(id + "," + name + "," + email + "," + country + "," + password);
			} while (rs.next());
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
		return gymOwner;
	}

	/**
	 * Adds a gym owner to the database
	 * @param gymOwnerDetails object
	 */
	public void addGymOwnerDetails(GymOwner gymOwnerDetails) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_INSERT_USER);
			preparedStatement.setString(1, gymOwnerDetails.getEmail());
			preparedStatement.setString(2, gymOwnerDetails.getPassword());
			preparedStatement.setString(3, "GymOwner");

			// Step 3: Execute the query or update query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// print SQL exception information
			printSQLException(e);
		}

		System.out.println(SQLConstants.SQL_INSERT_GYM_OWNER);
		// Step 1: Establishing a Connection
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_INSERT_GYM_OWNER);
			preparedStatement.setString(1, gymOwnerDetails.getEmail());
			preparedStatement.setString(2, gymOwnerDetails.getPassword());
			preparedStatement.setString(3, gymOwnerDetails.getName());
			preparedStatement.setString(4, gymOwnerDetails.getPhoneNumber());
			preparedStatement.setString(5, gymOwnerDetails.getAadharNumber());
			preparedStatement.setString(6, gymOwnerDetails.getPanNumber());
			preparedStatement.setBoolean(7, gymOwnerDetails.isVerified());

			// Step 3: Execute the query or update query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// print SQL exception information
			printSQLException(e);
		}
	}

	/**
	 * Edits gym owner details in the database
	 * @param gymOwnerDetails object
	 */

	// Bug if GymOwner updates email
	public int editGymOwnerDetails(GymOwner gymOwnerDetails) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_UPDATE_USER);
			preparedStatement.setString(1, gymOwnerDetails.getEmail());
			preparedStatement.setString(2, gymOwnerDetails.getPassword());
			preparedStatement.setString(3, "GymOwner");
			preparedStatement.setString(4, gymOwnerDetails.getEmail());
			// Step 3: Execute the query or update query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// print SQL exception information
			printSQLException(e);
		}

		//System.out.println(SQLConstants.SQL_UPDATE_GYM_OWNER);
		// Step 1: Establishing a Connection
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_UPDATE_GYM_OWNER);
			preparedStatement.setString(1, gymOwnerDetails.getEmail());
			preparedStatement.setString(2, gymOwnerDetails.getName());
			preparedStatement.setString(3, gymOwnerDetails.getPhoneNumber());
			preparedStatement.setString(4, gymOwnerDetails.getAadharNumber());
			preparedStatement.setString(5, gymOwnerDetails.getPanNumber());
			preparedStatement.setBoolean(6, gymOwnerDetails.isVerified());
			preparedStatement.setString(7, gymOwnerDetails.getEmail());
			// Step 3: Execute the query or update query
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// print SQL exception information
			printSQLException(e);
		}
		return 0;
	}

	/**
	 * Retrieves gym details from the database
	 * @param gymId The Id of the gym
	 * @return Gym object
	 */
	public Gym getGym(String gymId) {
		Connection connection = null;
		Gym gym = new Gym();
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_READ_GYM);
			preparedStatement.setString(1, gymId);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next())
				return null;
			// Step 4: Process the ResultSet object.
			do {
				gym.setGymId(rs.getString("gymId"));
				gym.setGymName(rs.getString("gymName"));
				gym.setOwnerEmail(rs.getString("gymOwnerEmail"));
				gym.setAddress(rs.getString("address"));
				gym.setSlotCount(rs.getInt("slotCount"));
				gym.setSeatsPerSlotCount(rs.getInt("seatsPerSlot"));
				gym.setVerified(rs.getBoolean("isVerified"));

//	                System.out.println(id + "," + name + "," + email + "," + country + "," + password);
			} while (rs.next());
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
		return gym;
	}

	/**
	 * Adds a gym to the database
	 * @param gymDetails object
	 */
	public void addGym(Gym gymDetails) {
		Connection connection = null;
		//System.out.println(SQLConstants.SQL_INSERT_GYM);
		// Step 1: Establishing a Connection
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_INSERT_GYM);
			preparedStatement.setString(1, gymDetails.getGymId());
			preparedStatement.setString(2, gymDetails.getGymName());
			preparedStatement.setString(3, gymDetails.getOwnerEmail());
			preparedStatement.setString(4, gymDetails.getAddress());
			preparedStatement.setInt(5, gymDetails.getSlotCount());
			preparedStatement.setInt(6, gymDetails.getSeatsPerSlotCount());
			preparedStatement.setBoolean(7, gymDetails.isVerified());

			// Step 3: Execute the query or update query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// print SQL exception information
			printSQLException(e);
		}
	}

	/**
	 * Edits a gym in the database
	 * @param gymDetails object
	 */
	public int editGym(Gym gymDetails) {
		Connection connection = null;
		//System.out.println(SQLConstants.SQL_UPDATE_GYM);
		// Step 1: Establishing a Connection
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_UPDATE_GYM);
			preparedStatement.setString(1, gymDetails.getGymId());
			preparedStatement.setString(2, gymDetails.getGymName());
			preparedStatement.setString(3, gymDetails.getOwnerEmail());
			preparedStatement.setString(4, gymDetails.getAddress());
			preparedStatement.setInt(5, gymDetails.getSlotCount());
			preparedStatement.setInt(6, gymDetails.getSeatsPerSlotCount());
			preparedStatement.setBoolean(7, gymDetails.isVerified());
			preparedStatement.setString(8, gymDetails.getGymId());

			// Step 3: Execute the query or update query
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// print SQL exception information
			printSQLException(e);
		}
		return 0;
	}

	/**
	 * Retrieves all gym details of a gym owner from the database
	 * @param gymOwnerId The Email Id of the gym owner
	 * @return List of Gym objects
	 */
	public List<Gym> getGymsOfGymOwner(String gymOwnerId) {
		Connection connection = null;
		List<Gym> gyms = new ArrayList<Gym>();
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_ALL_GYMS);
			preparedStatement.setString(1, gymOwnerId);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

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
//	                System.out.println(id + "," + name + "," + email + "," + country + "," + password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
		return gyms;
	}

	public Gym getGymById(String gymId) {
		Connection connection = null;
		Gym gym = null;
		try {
			connection = DBUtils.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_GYM_BY_ID);
			preparedStatement.setString(1, gymId);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				gym = new Gym();
				gym.setGymId(rs.getString("gymId"));
				gym.setGymName(rs.getString("gymName"));
				gym.setOwnerEmail(rs.getString("ownerEmail"));
				gym.setAddress(rs.getString("address"));
				gym.setSlotCount(rs.getInt("slotCount"));
				gym.setSeatsPerSlotCount(rs.getInt("seatsPerSlotCount"));
				gym.setVerified(rs.getBoolean("isVerified"));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return gym;
	}

	/**
	 * Retrieves all the possible slots of a gym from the database
	 * @param gymId The Id of the gym
	 * @return List of the Slot objects
	 */
	public List<Slot> getPossibleSlots(String gymId) {
		Connection connection = null;
		List<Slot> slots = new ArrayList<Slot>();
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_READ_SLOT_FROM_GYMID);
			preparedStatement.setString(1, gymId);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next())
				return null;
			// Step 4: Process the ResultSet object.
			do {
				Slot slot = new Slot();
				slot.setSlotId(rs.getString("slotId"));
				slot.setGymId(rs.getString("gymId"));
				slot.setStartTime(rs.getString("startTime"));
				slot.setEndTime(rs.getString("endTime"));
				slot.setTrainer(rs.getString("trainer"));
				slots.add(slot);
//	                System.out.println(id + "," + name + "," + email + "," + country + "," + password);
			} while (rs.next());
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
		return slots;
	}

	/**
	 * Adds a slot in the database
	 * @param slot object
	 * @throws UnauthorizedAccessException
	 */
	public boolean addSlot(Slot slot, String ownerEmail) throws UnauthorizedAccessException {
		Connection connection = null;
		// Step 1: Establishing a Connection
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatementAuthorize = connection.prepareStatement(SQLConstants.SQL_SELECT_GYM_WITH_OWNER);
			preparedStatementAuthorize.setString(1, slot.getGymId());
			preparedStatementAuthorize.setString(2, ownerEmail);
			ResultSet rs1 = preparedStatementAuthorize.executeQuery();
			if (!rs1.next())
				throw new UnauthorizedAccessException();

			PreparedStatement preparedStatementGym = connection.prepareStatement(SQLConstants.SQL_READ_GYM);
			preparedStatementGym.setString(1, slot.getGymId());
			ResultSet rs = preparedStatementGym.executeQuery();
			if (!rs.next())
				return false;
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_INSERT_SLOT);
			preparedStatement.setString(1, slot.getSlotId());
			preparedStatement.setString(2, slot.getGymId());
			preparedStatement.setString(3, slot.getDate());
			preparedStatement.setString(4, slot.getStartTime());
			preparedStatement.setString(5, slot.getEndTime());
			preparedStatement.setString(6, slot.getTrainer());
			preparedStatement.setInt(7, slot.getNumOfSeats());
			preparedStatement.setInt(8, slot.getNumOfSeatsBooked());

			// Step 3: Execute the query or update query
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {

			// print SQL exception information
			printSQLException(e);
		}
		return false;
	}

	/**
	 * Checks if a gym owner is verified by the Administrator
	 * @param email The emailId of the gym owner
	 */
	public boolean checkOwnerApproval(String email) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_VERIFICATION_STATUS_GYM_OWNER);
			preparedStatement.setString(1, email);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			return rs.next();
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
		return false;
	}

	/**
	 * Checks if a gym is verified by the Administrator
	 * @param gymId The Id of the gym
	 */
	public boolean checkGymApproval(String gymId) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_VERIFICATION_STATUS_GYM);
			preparedStatement.setString(1, gymId);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			return rs.next();
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
		return false;
	}

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