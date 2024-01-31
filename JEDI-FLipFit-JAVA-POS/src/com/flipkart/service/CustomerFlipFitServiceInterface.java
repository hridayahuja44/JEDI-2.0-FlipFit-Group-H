package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.exception.CustomerNotFoundException;
import com.flipkart.exception.SlotNotFoundException;

import java.util.*;

public interface CustomerFlipFitServiceInterface {
    public Customer getProfile(String email) throws CustomerNotFoundException;
    /*
    returns the customer profile
    */

    public void editProfile(Customer customer)  throws CustomerNotFoundException;
    /*
    allows the customer to edit profile
    */
    public List<Gym> getGymInCity(String city) ;
    public List<Slot> getSlotInGym(String gymId) throws SlotNotFoundException;
    public boolean isSlotBooked(String slotId, String date);
    public boolean cancelBooking(String bookingId, String email);
}