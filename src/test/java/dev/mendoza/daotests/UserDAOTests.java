package dev.mendoza.daotests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.UserDAO;
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
	
	@Test
	public void userGetUserTest() {
		User u = udao.getUser("chesmend");
		System.out.println(u);
	}
}
