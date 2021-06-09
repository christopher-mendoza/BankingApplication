package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.User;

public interface UserService {
	boolean addUser(User u);
	User getUser(String username);
	List<User> getAllUsers();
}
