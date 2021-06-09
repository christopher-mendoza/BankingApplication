package dev.mendoza.daos;

import java.nio.channels.ConnectionPendingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			cs.setFloat(3,  t.getAmount());
			cs.execute();
			cs.close();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Transaction getTransaction(Integer id) {
		String sql = "SELECT transaction_id, t_acc_num, transaction_type, t_amount "
				+ "FROM transactions WHERE transaction_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			//Checks if ResultSet returns nothing (bad input)
			if(!rs.isBeforeFirst()) {
				System.out.println("I could not find transaction \"" + id + "\". Please try again.");
				return null;
			}
			if(rs.next()) {
				Transaction t = new Transaction();
				t.setId(rs.getInt("transaction_id"));
				t.setAccNum(rs.getInt("t_acc_num"));
				t.setType(rs.getString("transaction_type").charAt(0));
				t.setAmount(rs.getFloat("t_amount"));
				return t;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Transaction> getAllTransactions() {
		String sql = "SELECT * FROM transactions;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Transaction> transactions = new ArrayList<Transaction>();
			while(rs.next()) {
				Transaction t = new Transaction();
				t.setId(rs.getInt("transaction_id"));
				t.setAccNum(rs.getInt("t_acc_num"));
				t.setType(rs.getString("transaction_type").charAt(0));
				t.setAmount(rs.getFloat("t_amount"));
				transactions.add(t);
			}
			return transactions;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
