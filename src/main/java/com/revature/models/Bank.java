package com.revature.models;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

	private String name;
	private ArrayList<User> users;
	private ArrayList<BankAccount> accounts;

	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<BankAccount>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<BankAccount> accounts) {
		this.accounts = accounts;
	}

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

	public String getNewUserUsername() {

		String username;
		boolean notUnique = false;

		do {
			username = "";
			for (User user : this.users) {
				if (username.compareTo(user.getUserName()) == 0) {
					notUnique = true;
					break;
				}
			}
		} while (notUnique);

		return username;
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

	/**
	 * Append a bank account to our accounts ArrayList
	 * 
	 * @param account
	 */
	public void addAccount(BankAccount account) {
		this.accounts.add(account);
	}

	/**
	 * Add a user to the bank, every user has at least 1 account
	 * 
	 * @param userName
	 * @param password
	 * @param pin
	 * @return
	 */
	public User addUser(String userName, String password, String pin) {
		User user = new User(userName, false, pin, this);
		users.add(user);

		String accountNum = getNewAccountUUID();
		BankAccount account = new BankAccount(accountNum, 0, user, this, "Checking", false);
		user.addAccount(account);

		return user;
	}
	
	public User addUser(String userName, String pin) {
		User user = new User(userName, false, pin, this);
		users.add(user);

		String accountNum = getNewAccountUUID();
		BankAccount account = new BankAccount(accountNum, 0, user, this, "Checking", false);
		user.addAccount(account);

		return user;
	}

	/**
	 * Iterate through User array and grab user with this ID and pin
	 * 
	 * @param userName
	 * @param pin
	 * @return
	 */
	public User userLogin(String userName, String pin) {
		for (User u : this.users) {
			if (u.getUuid().equals(userName) && u.validatePin(pin)) {
				return u;
			}
		}
		return null;
	}
	
	public User userLoginWithUsername(String userName, String pin) {
		for (User u : this.users) {
			if (u.getUserName().equals(userName) && u.validatePin(pin)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Bank [name=" + name + ", users=" + users + ", accounts=" + accounts + "]";
	}

}
