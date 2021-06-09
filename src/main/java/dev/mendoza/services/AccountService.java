package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.Account;
import dev.mendoza.models.User;

public interface AccountService {
	boolean addAccount(Account a);
	Account getAccount(Integer id);
	List<Account> getAllAccounts();
	List<Account> getAllUnapprovedAccounts();
	List<Account> getAllAccountsByUser(User u);
	boolean approve(Account a);
	boolean deny(Account a);
	boolean changeBal(Account a, float newBal);
	boolean withdraw(Account a, float with);
	boolean deposit(Account a, float dep);
	boolean transfer(Account a, Account b, float trans);
}
