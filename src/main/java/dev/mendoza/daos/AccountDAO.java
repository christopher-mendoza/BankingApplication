package dev.mendoza.daos;

import java.sql.CallableStatement;
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
public class Account {
	private Integer id;
	private String username;
	private Integer accNum;
	private Float balance;
	private Boolean approved;
*/

public class AccountDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public boolean add(Account a) {
		String sql = "INSERT INTO accounts VALUES (default, ?, ?, ?, FALSE) RETURNING *;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, a.getUsername());
			cs.setInt(2, a.getAccNum());
			cs.setFloat(3, a.getBalance());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Account getAccount(Integer accNum) {
		String sql = "SELECT account_id, a_username, a_acc_num, a_amount, approved "
				+ "FROM accounts WHERE a_acc_num = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  accNum);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet returns nothing (bad input)
			if(!rs.isBeforeFirst()) {
				System.out.println("I could not find the account \"" + accNum + "\". Please try again.");
				return null;
			}
			if(rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("account_id"));
				a.setUsername(rs.getString("a_username"));
				a.setAccNum(rs.getInt("a_acc_num"));
				a.setBalance(rs.getFloat("a_amount"));
				a.setApproved(rs.getBoolean("approved"));
				return a;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Account> getAllAccounts() {
		String sql = "SELECT * FROM accounts;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Account> accounts = new ArrayList<Account>();
			while(rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("account_id"));
				a.setUsername(rs.getString("a_username"));
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
	
	public boolean approve(Account change) {
		String sql = "CALL approve(?);";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, change.getAccNum());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deny(Account a) {
		String sql = "DELETE FROM accounts WHERE a_acc_num = ?;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, a.getAccNum());
			cs.execute();
			cs.close();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean changeBal(Account a, float newBal) {
		String sql = "CALL alter(?, ?);";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, a.getAccNum());
			cs.setFloat(2, newBal);
			cs.execute();
			cs.close();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
