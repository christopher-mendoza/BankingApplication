package dev.mendoza.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.mendoza.models.Account;
import dev.mendoza.models.User;
import dev.mendoza.utils.JDBCConnection;

/*
public class User {
	private Integer id;
	private String name;
	private String username;
	private String password;
	private Boolean admin;
*/

public class UserDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public boolean add(User u) {
		String sql = "INSERT INTO users VALUES (default, ?, ?, ?, FALSE) RETURNING *;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			boolean success = ps.execute();
			if(success) {
				ResultSet rs = ps.getResultSet();
				if(rs.next()) {
					u.setId(rs.getInt("user_id"));
					return true;
				}
				
			}
			else {
				System.out.println("Could not add user. Please try again.");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	
	}
	
	public User getUser(String username) {
		String sql = "SELECT user_id, name, u_username, password, admin "
				+ "FROM users WHERE u_username = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet would return nothing (bad input)
			if(!rs.isBeforeFirst()) {
				System.out.println("I could not find the user \"" + username + "\". Please try again.");
				return null;
			}
			if(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setName(rs.getString("name"));
				u.setUsername(rs.getString("u_username"));
				u.setPassword(rs.getString("password"));
				u.setAdmin(rs.getBoolean("admin"));
				return u;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM users";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setName(rs.getString("name"));
				u.setUsername(rs.getString("u_username"));
				u.setPassword(rs.getString("password"));
				u.setAdmin(rs.getBoolean("admin"));
				users.add(u);
			}
			return users;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
