package dev.mendoza.daotests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.UserDAO;
import dev.mendoza.models.Account;
import dev.mendoza.models.User;

public class UserDAOTests {

	private UserDAO udao = new UserDAO();

	@Test @Ignore
	public void userAddTest() {
		User u = new User("Chicken", "gamer", "soup");
		if(udao.add(u)) {
			System.out.println("User Add Test Works");
		}
	}
	
	@Test @Ignore
	public void userGetUserTest() {
		User u = udao.getUser("blabla");
		System.out.println(u);
	}
	
	@Test @Ignore
	public void userGetUserAccountsTest() {
		User u = new User("Marigold", "mari", "remedy");
		List<Account> a = udao.getUserAccounts(u);
		for(Account s : a) {
			System.out.println(s.getId());
			System.out.println(s.getAccNum());
			System.out.println(s.getBalance());
			System.out.println(s.getApproved());
		}
	}
	
	@Test @Ignore
	public void userGetAllTest() {
		List<User> users = udao.getAll();
		for(User u : users) {
			System.out.println(u.getId());
		}
	}
}
