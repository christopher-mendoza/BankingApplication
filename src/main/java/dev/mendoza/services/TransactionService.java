package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.Transaction;

public interface TransactionService {
	boolean addTransaction(Transaction t);
	Transaction getTransaction(Integer id);
	List<Transaction> getAllTransactions();
	List<Transaction> getAllTransactionsByAccount(Integer accNum);
}
