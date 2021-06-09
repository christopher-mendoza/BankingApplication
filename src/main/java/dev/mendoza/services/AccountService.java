package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.Account;
import dev.mendoza.models.User;

public interface AccountService {
	boolean addAccount(Account a);
	Account getAccount(Integer id);
	List<Account> getAllAccounts(User u);
	boolean approve(Account a);
	boolean deny(Account a);
	boolean changeBal(Account a, float newBal);
}
