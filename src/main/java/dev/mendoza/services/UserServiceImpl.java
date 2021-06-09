package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.UserDAO;
import dev.mendoza.models.User;

public class UserServiceImpl implements UserService {

	private static UserDAO udao = new UserDAO();
	
	@Override
	public boolean addUser(User u) {
		return udao.add(u);
	}

	@Override
	public User getUser(String username) {
		return udao.getUser(username);
	}


	@Override
	public List<User> getAllUsers() {
		return udao.getAll();
	}

	@Override
	public boolean updateUser(User u) {
		return udao.update(u);
	}

	@Override
	public boolean deleteUser(User u) {
		return udao.delete(u);
	}


}
