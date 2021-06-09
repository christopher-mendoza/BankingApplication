package dev.mendoza.daotests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.daos.TransactionDAO;
import dev.mendoza.models.Transaction;
import dev.mendoza.models.User;

public class TransactionDAOTests {

	TransactionDAO tdao = new TransactionDAO();
	
	@Test @Ignore
	public void transactionAddTest() {
		Transaction t = new Transaction(24862486, 'D', 1000.465f);
		if(tdao.add(t)) {
			System.out.println("Transaction Add Test Works");
		}
	}
	
	@Test @Ignore
	public void transactionGetTransactionTest() {
		Integer i = 7;
		System.out.println(tdao.getTransaction(i));
	}
	
	@Test @Ignore
	public void transactionGetAllTransactionsTest() {
		List<Transaction> transactions = tdao.getAllTransactions();
		for(Transaction t : transactions) {
			System.out.println(t);
		}
	}

}
