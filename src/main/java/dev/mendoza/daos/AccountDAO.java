package dev.mendoza.daos;

import java.sql.Connection;
import java.util.List;

import dev.mendoza.models.Account;
import dev.mendoza.utils.JDBCConnection;

public class AccountDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public Account add(Account a) {
		return null;
	}
	
	public Account getById(Integer id) {
		return null;
	}
	
	public List<Account> getAll() {
		return null;
	}
	
	public boolean update(Account change) {
		return false;
	}
	
	public boolean delete(Account a) {
		return false;
	}
}
