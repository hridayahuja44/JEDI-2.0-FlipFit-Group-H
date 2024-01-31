package com.flipkart.DAO;

import com.flipkart.bean.Customer;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;

public interface UserFlipFitDAO {
	public boolean authenticateUser(User user);

	public boolean registerCustomer(Customer customer);

	public boolean registerGymOwner(GymOwner gymOwner);
}
