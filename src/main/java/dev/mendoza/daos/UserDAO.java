package dev.mendoza.daos;

import java.sql.Connection;
import java.util.List;

import dev.mendoza.models.User;
import dev.mendoza.utils.JDBCConnection;

public class UserDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public User add(User u) {
		return null;
	}
	
	public User getById(Integer id) {
		return null;
	}
	
	public List<User> getAll() {
		return null;
	}
	
	public boolean update(User change) {
		return false;
	}
	
	public boolean delete(User u) {
		return false;
	}
}
