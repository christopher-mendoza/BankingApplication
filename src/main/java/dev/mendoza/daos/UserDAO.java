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
	private List<Account> accounts;
*/

public class UserDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public boolean add(User u) {
		String sql = "INSERT INTO users VALUES (default, ?, ?, ?, FALSE)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getUsername());
			ps.setString(3,  u.getPassword());
			boolean rs = ps.execute();
			return rs;
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
	
	public List<Account> getUserAccounts(User u) {
		String sql = "SELECT user_id, name, u_username, password, admin, "
				+ "account_id, a_acc_num, a_amount, approved "
				+ "FROM users "
				+ "LEFT JOIN accounts ON u_username = a_username "
				+ "WHERE u_username = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  u.getUsername());
			ResultSet rs = ps.executeQuery();
			List<Account> accounts = new ArrayList<Account>();
			while(rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("account_id"));
				a.setAccNum(rs.getInt("a_acc_num"));
				a.setBalance(rs.getFloat("a_amount"));
				a.setApproved(rs.getBoolean("approved"));
				accounts.add(a);
			}
			return accounts;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getAll() {
		String sql = "SELECT * FROM users";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("user_id"));
				users.add(u);
			}
			return users;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean update(User change) {
		return false;
	}
	
	public boolean delete(User u) {
		return false;
	}
}
