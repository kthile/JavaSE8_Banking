package com.revature.models;

import com.revature.models.BankAccount;
import java.io.Serializable;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Serializable {
	private static final long serialVersionUID = 6998291283486385327L;

	protected static String userName;
	protected transient static String password;
	private int accessLevel = 1;
	protected static boolean approved = false;
	private byte pinHash[];
	private ArrayList<BankAccount> accounts;
	private String uuid;

	public User(String userName, String password, boolean approved, String pin, Bank bank) {
		super();
		this.userName = userName;
		this.password = password;
		this.approved = approved;

		// md5 hashing
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// generate a uuid
		this.uuid = bank.getNewUserUUID();
		this.accounts = new ArrayList<BankAccount>();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public String toString() {
		return "Users [userName=" + userName + ", password=" + password + ", accessLevel=" + accessLevel + "approved="
				+ approved + "]";
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

}
