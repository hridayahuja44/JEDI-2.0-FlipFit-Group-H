package com.flipkart.service;


import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.exception.GymNotFoundException;
import com.flipkart.exception.GymOwnerNotFoundException;

import java.util.List;

public interface GymOwnerFlipFitServiceInterface {
    public GymOwner getProfile(String email) throws GymOwnerNotFoundException;
    /*
  returns the gym owner's profile
    */
    public void editProfile(GymOwner gymOwnerNews) throws GymOwnerNotFoundException;
    /*
allows the gym owner to edit profile
     */

    public boolean addGym(Gym gym);
    /*
allows the gym owner to add new gym
     */

    public void editGym(Gym gym) throws GymNotFoundException;
    /*
allows the gym owner to edit the gym information
     */

    public List<Gym> getGymDetail(String gymOwnerEmail);
    /*
returns the list of all gyms owned by the gym owner
     */

    public boolean isApproved(String email);
    /*
  returns true if the gym owner is approved else returns false
     */

    public boolean isGymApproved(String gymId);
    /*
  returns true if the gym  is approved else returns false
     */
}