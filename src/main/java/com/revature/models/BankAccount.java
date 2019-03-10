package com.revature.models;

import java.util.ArrayList;

public class BankAccount {
	protected static String accountNumber;
	protected static double balance;
	protected static User user;
	private String uuid;
	private ArrayList<Transaction> transactions;

	public BankAccount(String accountNumber, double balance, User user, Bank bank) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.user = user;

		this.uuid = bank.getNewAccountUUID();

		this.transactions = new ArrayList<Transaction>();
		user.addAccount(this);
		bank.addAccount(this);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", user=" + user + "]";
	}

}
