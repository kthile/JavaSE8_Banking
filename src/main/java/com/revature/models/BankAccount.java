package com.revature.models;

import java.util.ArrayList;

import com.revature.services.UserServices;

public class BankAccount {
	protected static String accountNumber; // should be arraylist
	protected static double balance;
	protected static User user;
	private String uuid;
	private ArrayList<Transaction> transactions;
	private String type;
	private boolean approved = false;

	public BankAccount(String accountNumber, double balance, User user, Bank bank, String type, boolean approved) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.user = user;
		this.type = type;
		this.approved = approved;

		this.uuid = bank.getNewAccountUUID();

		this.transactions = new ArrayList<Transaction>();
	}

	public static String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public String getSummaryLine() {
		// grab account bal
		double balance = this.balance;
		// format bal
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", this.uuid, this.getBalance(), this.type);
		} else {
			return String.format("%s : $(%.02f) : %s", this.uuid, this.getBalance(), this.type);
		}
	}

	/**
	 * Get the balance by adding each transaction
	 * 
	 * @return
	 */
	public double getBalance() {
		double balance = 0;
		for (Transaction transaction : this.transactions) {
			balance += transaction.getAmount();
		}
		return balance;
	}

	@Override
	public String toString() {
		return "BankAccount [uuid=" + uuid + ", transactions=" + transactions + "]";
	}

	public void printTransHistory() {
		System.out.println("Transaction history for account " + this.uuid + ", " + this.getUser().toString() + ":");
		for (int i = this.transactions.size() - 1; i >= 0; i--) {
			System.out.println(this.transactions.get(i).getSummaryLine());
		}
		System.out.println();

	}

	public void addTransaction(double amount, String memo) {
		Transaction t = new Transaction(amount, this, memo);
		this.transactions.add(t);
	}

	public boolean isApproved() {
		return approved;
	}

//	@Override
//	public String toString() {
//		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", user=" + this.user + "]";
//	}

}
