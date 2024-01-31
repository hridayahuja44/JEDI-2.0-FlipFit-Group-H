package com.flipkart.service;


import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;

import java.util.List;

public interface AdminFlipFitServiceInterface {
	 /*
    returns the list of all the gym owners
    */
    public List<GymOwner> getGymOwners();
   
    /*
    returns the list of all the gyms
    */
    public List<Gym> getGym() ;
    
    /*
    returns the list of all the gym owners whose requests are pending
    */
    public List<GymOwner> viewAllPendingGymOwnerRequests();
    
    /*
    approves the gym owner request whose email is passed
    */
    public boolean approveSingleGymOwnerRequest(String gymOwnerEmail);
   
    /*
    approves all the pending gym owner requests
    */
    public boolean approveAllPendingGymOwnerRequests();
    
    /*
    returns the list of all the gyms whose request is pending
   */
    public List<Gym> viewAllPendingGymRequests();
    
    /*
    approves single gym whose gymId is sent
    */
    public boolean approveSingleGymRequest(String gymId);
   
    /*
    approves all the pending gym requests
    */
    public boolean approveAllPendingGymRequests();
    
}
