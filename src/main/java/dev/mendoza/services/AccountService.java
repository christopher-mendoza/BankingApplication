package dev.mendoza.services;

import java.util.List;

import dev.mendoza.models.Account;

public interface AccountService {
	Account addAccount(Account a);
	Account getAccount(Integer id);
	List<Account> getAllAccounts();
	boolean updateAccount(Account a);
	boolean deleteAccount(Account a);
}
