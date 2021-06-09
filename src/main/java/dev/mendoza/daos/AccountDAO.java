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

public class AccountDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public Account add(Account a) {
		return null;
	}
	
	public Account getById(Integer id) {
		return null;
	}
	
	public List<Account> getAll(User u) {
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
	
	public boolean update(Account change) {
		return false;
	}
	
	public boolean delete(Account a) {
		return false;
	}
}
