package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.*;
import com.revature.services.*;

public class BankDriver {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		UserServices userService = new UserServices();
		List<User> userList = new ArrayList<>();
		int i = 0;
		
		while (sc.hasNext()) {
			System.out.println("****WELCOME TO THE BANK OF KHANG*****");
			System.out.println("=====================================");
			System.out.println("Are you 1.Applying alone, 2.Applying for a joint account, 3.Or logging in?");
			System.out.println("Enter an answer(1-3)");

			int answer = sc.nextInt();
			if (answer == 1) {
				// TODO Registration logic
				System.out.println("Username: ");
				String userName = sc.next();
				System.out.println("Password: ");
				String password = sc.next();

				User user = userService.apply(userName, password);
				userList.add(user);
				userService.writeUser(userList.get(i));

				System.out.println(userService.readUser());

			} else if (answer == 2) {
				// joint account logic

			} else {
				// TODO account verification logic
				System.out.println("Username: ");
				String userName = sc.next();
				System.out.println("Password: ");
				String password = sc.next();
				System.out.println("What would you like to do today? ");
				System.out.println("I want to.. 1.View my account information, 2.Make a deposit, 3.Make a withdrawal ");
				System.out.println("Enter an answer(1-3)");
				int choice = sc.nextInt();
				if (choice == 1) {
					// show account information
				} else if (choice == 2) {
					// make a deposit
				} else {
					// make a withdrawal
				}
				sc.close();
			}

		}
	}
}
