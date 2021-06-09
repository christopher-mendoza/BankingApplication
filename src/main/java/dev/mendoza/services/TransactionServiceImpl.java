package dev.mendoza.services;

import java.util.ArrayList;
import java.util.List;

import dev.mendoza.daos.TransactionDAO;
import dev.mendoza.models.Transaction;

public class TransactionServiceImpl implements TransactionService {

	private static TransactionDAO tdao = new TransactionDAO();
	
	@Override
	public boolean addTransaction(Transaction t) {
		return tdao.add(t);
	}

	@Override
	public Transaction getTransaction(Integer id) {
		return tdao.getTransaction(id);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return tdao.getAllTransactions();
	}

	@Override
	public List<Transaction> getAllTransactionsByAccount(Integer accNum) {
		List<Transaction> userTrans = new ArrayList<Transaction>();
		for(Transaction t : tdao.getAllTransactions()) {
			if(t.getAccNum().equals(accNum)) {
				userTrans.add(t);
			}
		}
		if(userTrans.isEmpty()) {
			System.out.println("No transactions made by this account.");
			return null;
		}
		return userTrans;
	}

}
