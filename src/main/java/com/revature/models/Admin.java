package com.revature.models;

public class Admin extends Employee {
	private int accessLevel = 3;
	private boolean approved = true;

	public Admin(String username, String password, boolean approved, String pin, Bank bank) {
		super(username, password, approved, pin, bank);
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
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
