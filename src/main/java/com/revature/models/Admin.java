package com.revature.models;

public class Admin extends Employee {
	private int accessLevel = 3;
	private boolean approved = true;

	public Admin(String userName, String password, boolean approved) {
		super(userName, password, approved);
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
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
