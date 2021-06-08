package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.Transaction;

public interface TransactionService {
	Transaction addTransaction(Transaction t);
	Transaction getTransaction(Integer id);
	List<Transaction> getAllTransactions();
	boolean updateTransaction(Transaction t);
	boolean deleteTransaction(Transaction t);
}
