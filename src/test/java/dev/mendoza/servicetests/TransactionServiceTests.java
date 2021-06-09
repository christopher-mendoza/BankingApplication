package dev.mendoza.servicetests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dev.mendoza.models.Transaction;
import dev.mendoza.services.TransactionServiceImpl;

public class TransactionServiceTests {

	private TransactionServiceImpl impl = new TransactionServiceImpl();
	
	@Test @Ignore
	public void getAllTransactionsByAccountTest() {
		Integer acc = 24862486;
		List<Transaction> trans = impl.getAllTransactionsByAccount(acc);
		for(Transaction t : trans) {
			System.out.println(t);
		}
	}
}
