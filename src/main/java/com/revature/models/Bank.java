package com.revature.models;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

	private String name;
	private ArrayList<User> users;
	private ArrayList<BankAccount> accounts;

	public String getNewUserUUID() {

		String uuid;
		Random rng = new Random();
		int len = 5;
		boolean notUnique = false;

		do {
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer) rng.nextInt(10)).toString();
			}

			for (User user : this.users) {
				if (uuid.compareTo(user.getUuid()) == 0) {
					notUnique = true;
					break;
				}
			}
		} while (notUnique);

		return uuid;
	}

	public String getNewAccountUUID() {
		String uuid;
		Random rng = new Random();
		int len = 5;
		boolean notUnique = false;

		do {
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer) rng.nextInt(10)).toString();
			}

			for (BankAccount account : this.accounts) {
				if (uuid.compareTo(account.getUuid()) == 0) {
					notUnique = true;
					break;
				}
			}
		} while (notUnique);

		return uuid;
	}

	public User addUser(String userName, String password, String pin) {
		User user = new User(userName, password, false, pin, this);
		users.add(user);
		
		String accountNum = getNewAccountUUID();

		BankAccount account = new BankAccount(accountNum, 100.00, user, this);
		user.addAccount(account);

		return user;
	}

}
