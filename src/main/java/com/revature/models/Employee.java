package com.revature.models;

public class Employee extends User {

	private static final long serialVersionUID = 1391931072366301535L;
	private int accessLevel = 2;
	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public boolean isApproved() {
		return approved;
	}

	private boolean approved = true;

	public Employee(String username, String password, boolean approved, String pin, Bank bank) {
		super(username, approved, pin, bank);
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
