package com.revature.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Bank;
import com.revature.models.BankAccount;
import com.revature.models.User;

public class UserServices {

	private static final String FILENAME = "bank_user.dat";

	public ArrayList<User> readUser(ArrayList<User> list, Bank bank) {

		ArrayList<User> bankUser = bank.getUsers();
		FileInputStream fileIn = null;
		ObjectInputStream in = null;

		try {
			fileIn = new FileInputStream(FILENAME);

			in = new ObjectInputStream(fileIn);

			bankUser = (ArrayList<User>) in.readObject();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bankUser;

	}

	public User readUser() {

		User bankUser = null;
		FileInputStream fileIn = null;
		ObjectInputStream in = null;

		try {
			fileIn = new FileInputStream(FILENAME);

			in = new ObjectInputStream(fileIn);

			bankUser = (User) in.readObject();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bankUser;

	}

	public void writeUser(User bankUser) {
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(FILENAME);

			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(bankUser);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void writeUser(ArrayList<BankAccount> accounts) {
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(FILENAME);

			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(accounts);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
