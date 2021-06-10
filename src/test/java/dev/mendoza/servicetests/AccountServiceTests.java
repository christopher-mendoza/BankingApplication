package dev.mendoza.servicetests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.models.Account;
import dev.mendoza.models.Transaction;
import dev.mendoza.models.User;
import dev.mendoza.services.AccountServiceImpl;

public class AccountServiceTests {

	private AccountServiceImpl impl = new AccountServiceImpl();
	
	@Test @Ignore
	public void depositTest() {
		Account a = new Account("bop", 24862486, 51020f);
		assertEquals(true, impl.deposit(a, 80.5f));
		System.out.println(impl.deposit(a, 80.5f));
	}
	
	@Test @Ignore
	public void withdrawTest() {
		Account a = new Account("bop", 24862486, 1000.64f);
		assertEquals(true, impl.withdraw(a, 500f));
		System.out.println(impl.withdraw(a, 500f));
	}
	
	@Test @Ignore
	public void transferTest() {
		Account a = new Account("bop", 24862486, 5000f);
		Account b = new Account("mari", 8008135, 5000f);
		assertEquals(true, impl.transfer(a, b, 4000f));
		System.out.println(impl.transfer(a, b, 4000f));
	}
	

	@Test @Ignore
	public void getAllAccountsByUserTest() {
		User u = new User("Marigold", "mari", "remedy");
		List<Account> accounts = impl.getAllAccountsByUser(u);
		for(Account a : accounts) {
			System.out.println(a);
		}
	}
	
	@Test
	public void getAllUnapprovedAccountsTest() {
		List<Account> unapproved = impl.getAllUnapprovedAccounts();
		for(Account a : unapproved) {
			System.out.println(a);
		}
	}
}
