package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.TransactionDAO;
import dev.mendoza.models.Transaction;

public class TransactionServiceImpl implements TransactionService {

	private static TransactionDAO tdao = new TransactionDAO();
	
	@Override
	public Transaction addTransaction(Transaction t) {
		return tdao.add(t);
	}

	@Override
	public Transaction getTransaction(Integer id) {
		return tdao.getById(id);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return tdao.getAll();
	}

	@Override
	public boolean updateTransaction(Transaction t) {
		return tdao.update(t);
	}

	@Override
	public boolean deleteTransaction(Transaction t) {
		return tdao.delete(t);
	}

}
