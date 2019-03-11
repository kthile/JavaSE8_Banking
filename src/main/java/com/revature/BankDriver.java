package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.revature.models.*;
import com.revature.services.*;


public class BankDriver {

	static Bank bank = new Bank("Bank of Khang");
	static UserServices userService = new UserServices();
	static List<User> list = new ArrayList<User>();
	static List<BankAccount> bankList = new ArrayList<BankAccount>();
	static LoggingService log;

	public static void main(String[] args) {

		User user = new User();
		Scanner sc = new Scanner(System.in);

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
		for (int i = 0; i < list.size(); i++) {
			user = list.get(0);

			// account to transfer from
			do {
				System.out.println("Enter a number (1-" + user.numAccounts() + ")"
						+ " for the account associated to transfer from");
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
					// System.out.println("Amount must be greater than 0");
					log.logError("Amount must be greater than 0");
				} else if (amount > acctBal) {
					// System.out.println("Amount must be less than your total balance:$" +
					// acctBal);
					log.logError("Amount should be less than your total balance:$" + acctBal);
				}
			} while (amount < 0 || amount > acctBal);

			// subtract from one account then add to the other
			user.addAccountTransaction(fromAccount, -1 * amount, "Transfer to account:" + user.getAcctUuid(toAccount));
			user.addAccountTransaction(toAccount, amount, "Transfer to account:" + user.getAcctUuid(fromAccount));
		}

	}

	private static void deposit(User user, Scanner sc) {
		int toAccount;
		double amount;
		double acctBal;
		String memo;
		user = list.get(0);

		// account to transfer into
		do {
			System.out.println(
					"Enter a number (1-" + user.numAccounts() + ")" + " for the account associated to deposit into");
			toAccount = sc.nextInt() - 1;
			if (toAccount < 0 || toAccount >= user.numAccounts()) {
				log.logError("Invalid account");
			}

		} while (toAccount < 0 || toAccount >= user.numAccounts());
		acctBal = user.getAccountBalance(toAccount);

		// amount to transfer
		do {
			System.out.println("Enter an amount to deposit (balance:" + "$" + user.getAccountBalance(toAccount) + ")");
			amount = sc.nextDouble();
			if (amount <= 0) {
				log.logError("Amount must be greater than 0");
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

		user = list.get(0);

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
			System.out.println("Enter an amount to withdraw (balance:" + "$" + acctBal + ")");
			amount = sc.nextDouble();
			if (amount <= 0) {
				log.logError("Amount must be greater than 0");
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

		user = list.get(0);

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
		User user;

		do {
			System.out.println("WELCOME TO " + bank.getName());

			System.out.println("Enter your username: ");
			userName = sc.nextLine();
			if (userName.length() > 10) {
				log.logError("Username can't be over 10 characters");
			}
			System.out.println("Enter your password: ");
			pin = sc.nextLine();
			if (pin.length() > 10) {
				log.logError("Password can't be over 10 characters");
			}

			user = bank.userLoginWithUsername(userName, pin);
			if (user == null) {
				log.logError("Invalid user id or password\n");
			}

		} while (user == null);

		return user;

	}

	// TODO make this function employees only
	private static void printUserMenu(User user, Scanner sc) {
		// print user account summary
		list.get(0).printAccounts();
		int choice;

		// user menu
		do {
			System.out.println("Welcome " + list.get(0).getUserName() + ". What would you like to do?");
			System.out.println(" 1. Show transaction history ");
			System.out.println(" 2. Withdraw ");
			System.out.println(" 3. Deposit ");
			System.out.println(" 4. Transfer ");
			System.out.println(" 5. Apply for an account ");
			System.out.println(" 6. Exit ");
			System.out.println("Choice: ");

			choice = sc.nextInt();

			if (choice < 1 || choice > 6) {
				log.logError("Incorrect input. Select-16");
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
				log.logError("Incorrect input. Select 1-2.");
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
		String username;
		String pin;
		System.out.println("Welcome, lets make your account!");
		System.out.println("What will your username be? (Max 10 characters)");
		username = sc.next();
		if (username.length() > 10) {
			log.logError("Username should not be over 10");
		}
		System.out.println("What will your password be? (Max 10 characters)");
		pin = sc.next();
		if (pin.length() > 10) {
			log.logError(pin);
		}
		System.out
				.println("We will start you off with an empty checking and savings account for now " + username + "\n");

		user = new User(username, false, pin, bank);
		BankAccount account = new BankAccount(BankAccount.getAccountNumber(), 0, user, bank, "Checking", false);
		BankAccount savings = new BankAccount(BankAccount.getAccountNumber(), 0, user, bank, "Savings", false);
		user.addAccount(account);
		user.addAccount(savings);
		bank.addUser(username, pin);
		list.add(user);

		userService.writeUser(list);
		System.out.println("Your info: " + userService.readUser(bank.getUsers(), bank) + "\n");

	}

}