/**
 * 
 */
package com.flipkart.bean;

/**
 * 
 */
public class Administrator extends User {
	private String name;
	private String phoneNumber;
	
	public Administrator(String email, String password, String roleName, String name, String phoneNumber) {
		super(email, password, roleName);
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public Administrator() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
