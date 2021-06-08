package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.User;

public interface UserService {
	User addUser(User u);
	User getUser(Integer id);
	List<User> getAllUsers();
	boolean updateUser(User u);
	boolean deleteUser(User u);
}
