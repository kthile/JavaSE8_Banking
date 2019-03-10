package com.revature.models;

public class Employee extends User {

	private int accessLevel;
	private boolean approved = true;

	public Employee(String username, String password, boolean approved) {
		super(userName, password, approved);
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "Users [userName=" + userName + ", password=" + password + ", accessLevel=" + this.accessLevel
				+ "approved=" + this.approved + "]";
	}

}
