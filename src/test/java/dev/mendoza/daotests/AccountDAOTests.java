package dev.mendoza.daotests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.AccountDAO;
import dev.mendoza.models.Account;
import dev.mendoza.models.User;

public class AccountDAOTests {

	AccountDAO adao = new AccountDAO();
	
	@Test
	public void accountAddTest() {
		Account a = new Account("mari", 123456789, 20000.45f);
		assertEquals(true, adao.add(a));
		if(adao.add(a)) {
			System.out.println("Account Add Test Works");
		}
	}

	@Test @Ignore
	public void accountGetAccountTest() {
		Integer i = 8008135;
		System.out.println(adao.getAccount(i));
	}
	
	@Test @Ignore
	public void accountGetAllTest() {
		List<Account> accounts = new ArrayList<Account>();
		accounts = adao.getAllAccounts();
		for(Account a : accounts) {
			System.out.println(a);
		}
	}
	
	@Test 
	public void accountApproveTest() {
		Account a = new Account("mari", 1234567, 20000.45f);
		assertEquals(true, adao.approve(a));
		System.out.println(adao.approve(a));
	}
	
	@Test 
	public void accountDenyTest() {
		Account a = new Account("mari", 12345678, 20000.45f);
		assertEquals(true, adao.deny(a));
		System.out.println(adao.deny(a));
	}
	
	@Test
	public void accountChangeBalTest() {
		Account a = new Account("mari", 123456, 20000.45f);
		float money = 1000.69f;
		assertEquals(true, adao.changeBal(a, money));
		System.out.println(adao.changeBal(a, money));
	}
}
