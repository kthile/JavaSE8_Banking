package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.revature.models.*;
import com.revature.services.*;

public class BankDriver {

	static Bank bank = new Bank("Bank of Khang");
	static User user;

	public static void main(String[] args) {

		UserServices userService;
		Scanner sc = new Scanner(System.in);

		bank.addUser("khang", "1234");

		// User curUser;
		while (true) {
			BankDriver.printLoginMenu(user, sc);
			BankDriver.mainMenuPrompt(sc);
			BankDriver.printUserMenu(user, sc);
		}

	}// end of main

	private static void transfer(User user, Scanner sc) {
		int fromAccount; // the index
		int toAccount;
		double amount;
		double acctBal;

		// account to transfer from
		do {
			System.out.println(
					"Enter a number (1-" + user.numAccounts() + ")" + " for the account associated to transfer from");
			fromAccount = sc.nextInt() - 1;
			if (fromAccount < 0 || fromAccount >= user.numAccounts())
				;

		} while (fromAccount < 0 || fromAccount >= user.numAccounts());
		acctBal = user.getAccountBalance(fromAccount);

		// account to transfer into
		do {
			System.out.println(
					"Enter a number (1-" + user.numAccounts() + ")" + " for the account associated to transfer to");
			toAccount = sc.nextInt() - 1;
			if (toAccount < 0 || toAccount >= user.numAccounts())
				;

		} while (toAccount < 0 || toAccount >= user.numAccounts());

		// amount to transfer
		do {
			System.out.println("Enter an amount to transfer (max:" + "$" + acctBal + ")");
			amount = sc.nextDouble();
			if (amount <= 0) {
				System.out.println("Amount must be greater than 0");
			} else if (amount > acctBal) {
				System.out.println("Amount must be less than your total balance:$" + acctBal);
			}
		} while (amount < 0 || amount > acctBal);

		// subtract from one account then add to the other
		user.addAccountTransaction(fromAccount, -1 * amount, "Transfer to account:" + user.getAcctUuid(toAccount));
		user.addAccountTransaction(toAccount, amount, "Transfer to account:" + user.getAcctUuid(fromAccount));

	}

	private static void deposit(User user, Scanner sc) {
		int toAccount;
		double amount;
		double acctBal;
		String memo;

		// account to transfer into
		do {
			System.out.println(
					"Enter a number (1-" + user.numAccounts() + ")" + " for the account associated to deposit into");
			toAccount = sc.nextInt() - 1;
			if (toAccount < 0 || toAccount >= user.numAccounts()) {
				System.out.println("Invalid account.");
			}

		} while (toAccount < 0 || toAccount >= user.numAccounts());
		acctBal = user.getAccountBalance(toAccount);

		// amount to transfer
		do {
			System.out.println("Enter an amount to deposit (balance:" + "$" + user.getAccountBalance(toAccount) + ")");
			amount = sc.nextDouble();
			if (amount <= 0) {
				System.out.println("Amount must be greater than 0");
			}
		} while (amount < 0);

		// clear previous input
		sc.nextLine();

		System.out.print("Enter a memo for this transaction: ");
		memo = sc.nextLine();

		user.addAccountTransaction(toAccount, amount, memo);

	}

	private static void withdraw(User user, Scanner sc) {
		int fromAccount; // the index
		int toAccount;
		double amount;
		double acctBal;
		String memo;

		// account to transfer from
		do {
			System.out.println(
					"Enter a number (1-" + user.numAccounts() + ")" + " for the account associated to withdraw from");
			fromAccount = sc.nextInt() - 1;
			if (fromAccount < 0 || fromAccount >= user.numAccounts())
				;

		} while (fromAccount < 0 || fromAccount >= user.numAccounts());
		acctBal = user.getAccountBalance(fromAccount);

		// amount to transfer
		do {
			System.out.println("Enter an amount to transfer (balance:" + "$" + acctBal + ")");
			amount = sc.nextDouble();
			if (amount <= 0) {
				System.out.println("Amount must be greater than 0");
			} else if (amount > acctBal) {
				System.out.println("Amount must be less than your total balance:$" + acctBal);
			}
		} while (amount < 0 || amount > acctBal);

		// clear previous input
		sc.nextLine();

		System.out.print("Enter a memo for this transaction: ");
		memo = sc.nextLine();

		user.addAccountTransaction(fromAccount, -1 * amount, memo);

	}

	public static void showTransHistory(User user, Scanner sc) {
		int accountChoice;
		// get all transaction history to look at
		do {
			System.out.println("Enter a number (1-" + user.numAccounts() + ")" + " for the account associated");
			accountChoice = sc.nextInt() - 1;
			if (accountChoice < 0 || accountChoice >= user.numAccounts()) {
				System.out.println("Invalid choice. Try again");
			}
		} while (accountChoice < 0 || accountChoice >= user.numAccounts());

		user.printAccountTransHistory(accountChoice);
	}

	private static User mainMenuPrompt(Scanner sc) {
		String userName;
		String pin;
		// User user;

		do {
			System.out.println("WELCOME TO " + bank.getName());
//			System.out.println("Enter your id: ");
//			userName = sc.nextLine();
//			System.out.println("Enter your pin: ");
//			pin = sc.nextLine();

			System.out.println("Enter your username: ");
			userName = sc.nextLine();
			System.out.println("Enter your pin: ");
			pin = sc.nextLine();

			// get user object same as entered uid and pin
//			user = bank.userLogin(userName, pin);
			user = bank.userLoginWithUsername(userName, pin);
			if (user == null) {
				System.out.println("Invalid user id or pin\n");
			}

		} while (user == null);

		return user;

	}

	// TODO make this function employees only
	private static void printUserMenu(User user, Scanner sc) {
		// print user account summary
		user.printAccounts();
		int choice;

		// user menu
		do {
			System.out.println("Welcome " + user.getUserName() + ". What would you like to do?");
			System.out.println(" 1. Show transaction history ");
			System.out.println(" 2. Withdraw ");
			System.out.println(" 3. Deposit ");
			System.out.println(" 4. Transfer ");
			System.out.println(" 5. Apply for an account ");
			System.out.println(" 6. Exit ");
			System.out.println("Choice: ");

			choice = sc.nextInt();

			if (choice < 1 || choice > 6) {
				System.out.println("Incorrect input. Select 1-5.");
			}
		} while (choice < 1 || choice > 6);

		switch (choice) {
		case 1:
			BankDriver.showTransHistory(user, sc);
			break;
		case 2:
			BankDriver.withdraw(user, sc);
			break;
		case 3:
			BankDriver.deposit(user, sc);
			break;
		case 4:
			BankDriver.transfer(user, sc);
			break;
		case 5:
			BankDriver.applyForAccount();
			break;
		case 6:
			sc.nextLine();
			break;
		}

		if (choice != 6) {
			BankDriver.printUserMenu(user, sc);
		}
	}

	private static void applyForAccount() {

		//TODO append an account and username to unapproved list
		System.out.println("Your application request is now on file");
	}

	private static void printLoginMenu(User user, Scanner sc) {
		int choice;
		// login menu
		do {
			System.out.println("Welcome, what would you like to do?");
			System.out.println(" 1. Register ");
			System.out.println(" 2. Login ");
			System.out.println("Choice: ");

			choice = sc.nextInt();

			if (choice < 1 || choice > 2) {
				System.out.println("Incorrect input. Select 1-2.");
			}
		} while (choice < 1 || choice > 2);

		switch (choice) {
		case 1:
			BankDriver.registerUser(user, sc);
			break;
		case 2:
			sc.nextLine();
			break;

		}

		if (choice != 2) {
			BankDriver.printLoginMenu(user, sc);
		}
	}

	private static void registerUser(User user, Scanner sc) {
		// create a new user
		// let them know it has been created
		// write to file
		// show this menu again
		String username;
		String pin;
		BankAccount account = null;

		System.out.println("Welcome, lets make your account!");
		System.out.println("What will your username be? (Max 10 characters)");
		username = sc.next();
		System.out.println("What will your pin be? (Max 4 characters)");
		pin = sc.next();
		System.out.println("We will start you off with an empty checking account for now " + username + "\n");

		user = new User(username, false, pin, bank);

		bank.addUser(username, pin);

		// System.out.println(bank.getUsers());

//		Bank bank = new Bank("Bank of Khang");
//		User user = bank.addUser("khang", "password", "1234");
//		UserServices userService;
//
//		BankAccount account = new BankAccount(BankAccount.getAccountNumber(), 0, user, bank, "Checking");
//		bank.addAccount(account);

	}

}

/*
 * 
 * 
 * Scanner sc = new Scanner(System.in); UserServices userService = new
 * UserServices(); List<User> userList = new ArrayList<>(); int i = 0;
 * 
 * while (sc.hasNext()) {
 * System.out.println("****WELCOME TO THE BANK OF KHANG*****");
 * System.out.println("====================================="); System.out.
 * println("Are you 1.Applying alone, 2.Applying for a joint account, 3.Or logging in?"
 * ); System.out.println("Enter an answer(1-3)");
 * 
 * int answer = sc.nextInt(); if (answer == 1) { // TODO Registration logic
 * System.out.println("Username: "); String userName = sc.next();
 * System.out.println("Password: "); String password = sc.next();
 * 
 * User user = userService.apply(userName, password); userList.add(user);
 * userService.writeUser(userList.get(i));
 * 
 * System.out.println(userService.readUser());
 * 
 * } else if (answer == 2) { // joint account logic
 * 
 * } else { // TODO account verification logic System.out.println("Username: ");
 * String userName = sc.next(); System.out.println("Password: "); String
 * password = sc.next();
 * System.out.println("What would you like to do today? "); System.out.
 * println("I want to.. 1.View my account information, 2.Make a deposit, 3.Make a withdrawal "
 * ); System.out.println("Enter an answer(1-3)"); int choice = sc.nextInt(); if
 * (choice == 1) { // show account information } else if (choice == 2) { // make
 * a deposit } else { // make a withdrawal } sc.close(); }
 * 
 * }
 */