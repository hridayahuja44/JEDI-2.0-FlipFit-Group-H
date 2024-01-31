package com.flipkart.service;



import com.flipkart.bean.Customer;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;
import com.flipkart.exception.UserAlreadyExistsException;
import com.flipkart.exception.UserNotFoundException;

public interface UserFlipFitServiceInterface {
    /*
   Registers a new customer
   @return boolean value indicating success of registration
   */
    public void registerCustomer(Customer customer) throws UserAlreadyExistsException;

    /*
    Registers a new Gym Owner
    @return boolean value indicating success of registration
    */
    public void registerGymOwner(GymOwner gymOwner) throws UserAlreadyExistsException;

    /*
    Authenticates a user
    @return boolean value indicating if user is authenticated
    */
    public void authenticateUser(User user) throws UserNotFoundException;

    /*
    Logs out a user
    @return boolean value indicating success of logout
    */
    public boolean logout(User user);

}