package com.revature.models;

public class Employee extends User {

	private int accessLevel;
	private boolean approved = true;

	public Employee(String username, String password, boolean approved, String pin, Bank bank) {
		super(username, password, approved, pin, bank);
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "Users [userName=" + username + ", password=" + password + ", accessLevel=" + this.accessLevel
				+ "approved=" + this.approved + "]";
	}

}
