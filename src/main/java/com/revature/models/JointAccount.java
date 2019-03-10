package com.revature.models;

import java.util.Arrays;

public class JointAccount extends BankAccount{

	User[] users = new User[1]; // joint accounts are 2 people max

	public JointAccount(User[] users) {
		super(accountNumber, balance, user);
		this.users = users;
	}

	public User[] getUsers() {
		return users;
	}

	public void setUsers(User[] users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "JointAccount [users=" + Arrays.toString(users) + "]";
	}

}
