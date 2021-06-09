package dev.mendoza.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dev.mendoza.models.Transaction;
import dev.mendoza.utils.JDBCConnection;

/*
public class Transaction {
	private Integer id;
	private Integer accNum;
	private Character type;
	private Float amount;
*/

public class TransactionDAO {

	private Connection conn = JDBCConnection.getConnection();
	
	public boolean add(Transaction t) {
		String sql = "INSERT INTO transactions VALUES (default, ?, ?, ?);";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, t.getAccNum());
			cs.setString(2, String.valueOf(t.getType()));
			cs.execute();
			cs.close();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Transaction getById(Integer id) {
		return null;
	}
	
	public List<Transaction> getAll() {
		return null;
	}
}
