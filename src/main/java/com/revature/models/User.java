package com.revature.models;

import com.revature.models.BankAccount;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//write out all accounts
//check if account is unique

public class User implements Serializable {
	private static final long serialVersionUID = 6998291283486385327L;

	protected String username;
	protected transient String password;
	private int accessLevel = 1;
	private byte pinHash[];
	private ArrayList<BankAccount> accounts;
	private String uuid;

	public User(String userName, String password, String pin, Bank bank) {
		super();
		this.username = userName;
		this.password = password;

		// md5 hashing
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// generate a uuid
		// this.uuid = bank.getNewUserUUID();
		this.accounts = new ArrayList<BankAccount>();
		// System.out.println("Registration success! Your UUID is :" + this.uuid);
		// System.out.println("Registration success! Your username is: " +
		// this.getUserName());
	}

	public User(String userName, boolean approved, String pin, Bank bank) {
		super();
		this.username = userName;

		// md5 hashing
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.accounts = new ArrayList<BankAccount>();
		//System.out.println("Registration success! Your username is: " + this.getUserName());

	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void addAccount(BankAccount bankAccount) {
		this.accounts.add(bankAccount);

	}

	public byte[] getPinHash() {
		return pinHash;
	}

	public void setPinHash(byte[] pinHash) {
		this.pinHash = pinHash;
	}

	public ArrayList<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Check if input pin = user's pin
	 * 
	 * @param pin
	 * @return
	 */
	public boolean validatePin(String pin) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Print all account information
	 */
	public void printAccounts() {
		System.out.println("Accounts listed for " + this.username + ":");
		for (int i = 0; i < this.accounts.size(); i++) {
			System.out.printf("%d) %s\n", i + 1, this.accounts.get(i).getSummaryLine());
		}
		System.out.println();
	}

	/**
	 * Counts the number of accounts in this.accounts arraylist
	 * 
	 * @return
	 */
	public int numAccounts() {
		return accounts.size();
	}

	public void printAccountTransHistory(int accountIndex) {
		this.accounts.get(accountIndex).printTransHistory();
	}

	public double getAccountBalance(int index) {
		return this.accounts.get(index).getBalance();
	}

	public String getAcctUuid(int index) {
		return this.accounts.get(index).getUuid();
	}

	public void addAccountTransaction(int index, double amount, String memo) {
		this.accounts.get(index).addTransaction(amount, memo);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", accounts=" + accounts + "]";
	}

//	@Override
//	public String toString() {
//		return "Users [userName=" + userName + ", password=" + password + ", accessLevel=" + accessLevel + "approved="
//				+ approved + "]";
//	}

}
