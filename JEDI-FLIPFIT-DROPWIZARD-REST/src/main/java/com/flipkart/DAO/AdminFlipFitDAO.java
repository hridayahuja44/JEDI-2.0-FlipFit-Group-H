package com.flipkart.DAO;

import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;

import java.util.List;

public interface AdminFlipFitDAO {
	/**
	 * Retrieves a list of all gym owners from database
	 * @return List of GymOwner objects
	 */
	public List<GymOwner> getAllGymOwners();

	/**
	 * Retrieves a list of all gyms from database
	 * @return List of Gym objects
	 */
	public List<Gym> getAllGyms();

	/**
	 * Retrieves a list of all pending gym owner requests from database
	 * @return List of GymOwner objects
	 */
	public List<GymOwner> getPendingGymOwnerRequests();

	/**
	 * Retrieves a list of all pending gym requests from database
	 * @return List of Gym objects
	 */
	public List<Gym> getPendingGymRequests();

	/**
	 * Approves a single gym owner request
	 * @param gymOwnerEmail The Email of the gym owner
	 * @return
	 */
	public int approveSingleOwnerRequest(String gymOwnerEmail);

	/**
	 * Approves all pending gym owner requests
	 * @return
	 */
	public int approveAllOwnerRequest();

	/**
	 * Approves a single gym request
	 * @param gymId The Id of the gym
	 * @return
	 */
	public int approveSingleGymRequest(String gymId);

	/**
	 * Approves all pending gym requests
	 * @return
	 */
	public int approveAllGymRequest();
}