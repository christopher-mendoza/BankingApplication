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
	
	@Test @Ignore
	public void accountAddTest() {
		Account a = new Account("slurp", 123456789, 20000.45f);
		if(adao.add(a)) {
			System.out.println("Account Add Test Works");
		}
	}

	@Test @Ignore
	public void accountGetAccountTest() {
		Integer i = 8008135;
		System.out.println(adao.getAccount(i));
	}
	
	@Test
	public void accountGetAllTest() {
		List<Account> accounts = new ArrayList<Account>();
		accounts = adao.getAllAccounts();
		for(Account a : accounts) {
			System.out.println(a);
		}
	}
	
	@Test @Ignore
	public void accountApproveTest() {
		Account a = new Account("slurp", 1234567, 20000.45f);
		System.out.println(adao.approve(a));
	}
	
	@Test @Ignore
	public void accountDenyTest() {
		Account a = new Account("slurp", 12345678, 20000.45f);
		System.out.println(adao.deny(a));
	}
	
	@Test @Ignore
	public void accountChangeBalTest() {
		Account a = new Account("slurp", 123456, 20000.45f);
		float money = 1000.69f;
		System.out.println(adao.changeBal(a, money));
	}
}
