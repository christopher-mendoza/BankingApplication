package dev.mendoza.daos;

import java.sql.Connection;
import java.util.List;

import dev.mendoza.models.Transaction;
import dev.mendoza.utils.JDBCConnection;

public class TransactionDAO {

	private Connection conn = JDBCConnection.getConnection();
	
	public Transaction add(Transaction t) {
		return null;
	}
	
	public Transaction getById(Integer id) {
		return null;
	}
	
	public List<Transaction> getAll() {
		return null;
	}
	
	public boolean update(Transaction change) {
		return false;
	}
	
	public boolean delete(Transaction t) {
		return false;
	}
}
