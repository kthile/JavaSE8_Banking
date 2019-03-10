package com.revature.models;

import java.util.Date;

public class Transaction {

	private double amount;
	private Date timestamp;
	private String memo;
	private BankAccount account;

	public Transaction(double amount, BankAccount account) {
		this.amount = amount;
		this.account = account;
		this.timestamp = new Date();
	}

	public Transaction(double amount, BankAccount account, String memo) {
		this(amount, account);
		this.memo = memo;
	}

	public double getAmount() {
		return this.amount;
	}
	

	public String getSummaryLine() {
		if (this.amount >= 0) {
			return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
		} else {
			return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.memo);
		}
	}

}
