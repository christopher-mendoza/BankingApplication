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
		User u = new User("Chicken", "gggamer", "soup");
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
	public void userGetAllUsersTest() {
		List<User> users = udao.getAllUsers();
		for(User u : users) {
			System.out.println(u);
		}
	}
}
