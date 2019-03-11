package com.revature;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.models.Admin;
import com.revature.models.Bank;
import com.revature.models.BankAccount;
import com.revature.models.Employee;
import com.revature.models.Transaction;
import com.revature.models.User;

public class MyTest {
	private static final Transaction testTransaction = new Transaction(100, null);
	private static final BankDriver driver = new BankDriver();
	private static final User user = new User();
	private static final Bank bank = new Bank("Bank");
	private static final BankAccount account = new BankAccount("1234", 0, user, bank, "checking", false);
	private static final Employee emp = new Employee("name", "1234", true, "pin", bank);
	private static final Admin adm = new Admin("name", "1234", true, "pin", bank);
	
	
	// NONE OF THESE SHOULD BE NULL
	@Test
	public void testGetUserList() {
		assertNotNull(BankDriver.list); //check if user arraylist not null
	}
	@Test 
	public void testNegative() {
		assertNotNull(testTransaction.getAmount()); 
	}
	@Test
	public void testNoBankUsers() {
		assertNotNull(BankDriver.bankList);
	}
	@Test
	public void testAddAccount() {
		assertEquals(account.isApproved(), false);
	}
	@Test
	public void testUser() {// regular user access level = 1
		assertEquals(user.getAccessLevel(), 1);
	}
	@Test
	public void testEmployee() {//employee access level = 2
		assertEquals(emp.getAccessLevel(), 2);
	}
	@Test
	public void testAdmin() {//admin access level = 3
		assertEquals(adm.getAccessLevel(), 3);
	}
}
