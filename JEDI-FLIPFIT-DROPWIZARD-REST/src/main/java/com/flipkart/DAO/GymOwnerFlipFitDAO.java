package com.flipkart.DAO;

import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.exception.UnauthorizedAccessException;

import java.util.List;

public interface GymOwnerFlipFitDAO {
	/**
	 * Retrieves gym owner details from the database
	 * @param gymOwnerEmailId The Email Id of the gym owner
	 * @return GymOwner object
	 */
	public GymOwner getGymOwnerDetails(String gymOwnerEmailId);

	/**
	 * Adds a gym owner to the database
	 * @param gymOwnerDetails object
	 */
	public void addGymOwnerDetails(GymOwner gymOwnerDetails);

	/**
	 * Edits gym owner details in the database
	 * @param gymOwnerDetails object
	 * @return
	 */
	public int editGymOwnerDetails(GymOwner gymOwnerDetails);

	/**
	 * Retrieves gym details from the database
	 * @param gymId The Id of the gym
	 * @return Gym object
	 */
	public Gym getGym(String gymId);

	/**
	 * Adds a gym to the database
	 * @param gymDetails object
	 */
	public void addGym(Gym gymDetails);

	/**
	 * Edits a gym in the database
	 * @param gymDetails object
	 * @return
	 */
	public int editGym(Gym gymDetails);

	/**
	 * Retrieves all gym details of a gym owner from the database
	 * @param gymOwnerId The Email Id of the gym owner
	 * @return List of Gym objects
	 */
	public List<Gym> getGymsOfGymOwner(String gymOwnerId);

	/**
	 * Retrieves all the possible slots of a gym from the database
	 * @param gymId The Id of the gym
	 * @return List of the Slot objects
	 */
	public List<Slot> getPossibleSlots(String gymId);

	/**
	 * Adds a slot in the database
	 * @param slot object
	 * @param ownerEmail
	 * @return if slot is generated
	 * @throws UnauthorizedAccessException
	 */
	public boolean addSlot(Slot slot, String ownerEmail) throws UnauthorizedAccessException;

	/**
	 * Checks if a gym owner is verified by the Administrator
	 * @param email The emailId of the gym owner
	 */
	public boolean checkOwnerApproval(String email);

	/**
	 * Checks if a gym is verified by the Administrator
	 * @param gymId The Id of the gym
	 */
	public boolean checkGymApproval(String gymId);
}