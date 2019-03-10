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

import com.revature.models.User;

public class UserServices {

	private static final String FILENAME = "bank_user.dat";

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

	public User apply(String userName, String password) {
		User user = new User(userName, password, false);
		System.out.println("New application for.. " + userName + " password = " + user.getPassword());

		return user;
	}

	public User[] applyForJointAccount(String userName, String password, String userName2, String password2) {
		User[] users = new User[1];
		users[0] = new User(userName, password, false);
		users[1] = new User(userName2, password2, false);
		System.out.println("New joint application for.. " + users[0] + " and " + users[1]);

		return users;
	}

}
