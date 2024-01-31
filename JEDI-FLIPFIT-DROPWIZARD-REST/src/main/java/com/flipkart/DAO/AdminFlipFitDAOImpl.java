/**
 *
 */
package com.flipkart.DAO;

import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.constants.SQLConstants;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AdminFlipFitDAOImpl implements AdminFlipFitDAO {
	/**
	 * Retrieves a list of all gym owners from the database
	 *
	 * @return List of GymOwner objects
	 */
	public List<GymOwner> getAllGymOwners() {
		Connection connection = null;
		List<GymOwner> gymOwners = new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_ALL_GYM_OWNERS);
			//System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				GymOwner gymOwner = new GymOwner();
				gymOwner.setEmail(rs.getString("email"));
				gymOwner.setName(rs.getString("name"));
				gymOwner.setPhoneNumber(rs.getString("phoneNum"));
				gymOwner.setAadharNumber(rs.getString("aadharNum"));
				gymOwner.setPanNumber(rs.getString("panNum"));
				gymOwner.setVerified(rs.getBoolean("isVerified"));
				gymOwners.add(gymOwner);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
		return gymOwners;
	}

	/**
	 * Retrieves a list of all gyms from the database
	 *
	 * @return List of Gym objects
	 */
	public List<Gym> getAllGyms() {
		Connection connection = null;
		List<Gym> gyms = new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_ALL_GYMS_WITHOUT_FILTER);
			//System.out.println(preparedStatement);
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
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
		return gyms;
	}

	/**
	 * Retrieves a list of all pending gym owner requests from the database
	 *
	 * @return List of GymOwner objects
	 */
	public List<GymOwner> getPendingGymOwnerRequests() {
		Connection connection = null;
		List<GymOwner> gymOwners = new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_PENDING_GYM_OWNERS);
			//System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			preparedStatement.setBoolean(1, false);
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				GymOwner gymOwner = new GymOwner();
				gymOwner.setEmail(rs.getString("email"));
				gymOwner.setName(rs.getString("name"));
				gymOwner.setPhoneNumber(rs.getString("phoneNum"));
				gymOwner.setAadharNumber(rs.getString("aadharNum"));
				gymOwner.setPanNumber(rs.getString("panNum"));
				gymOwner.setVerified(rs.getBoolean("isVerified"));
				gymOwners.add(gymOwner);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
		return gymOwners;

	}

	/**
	 * Retrieves a list of all pending gym requests from the database
	 *
	 * @return List of Gym objects
	 */
	public List<Gym> getPendingGymRequests() {
		Connection connection = null;
		List<Gym> gyms = new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_PENDING_GYMS);
			//System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			preparedStatement.setBoolean(1, false);
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
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
		return gyms;

	}

	/**
	 * Approves a single gym owner request
	 *
	 * @param gymOwnerEmail The Email of the gym owner
	 */
	public int approveSingleOwnerRequest(String gymOwnerEmail) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_APPROVE_GYM_OWNER_BY_ID);
			//System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			preparedStatement.setString(1, gymOwnerEmail);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return 0;
	}

	/**
	 * Approves all pending gym owner requests
	 */
	public int approveAllOwnerRequest() {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_APPROVE_ALL_GYMS_OWNERS);
			//System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return 0;
	}

	/**
	 * Approves a single gym request
	 *
	 * @param gymId The Id of the gym
	 */
	public int approveSingleGymRequest(String gymId) {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_APPROVE_GYM_BY_ID);
			//System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			preparedStatement.setString(1, gymId);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return 0;
	}

	/**
	 * Approves all pending gym requests
	 */
	public int approveAllGymRequest() {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_APPROVE_ALL_GYMS);
			//System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return 0;
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
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
