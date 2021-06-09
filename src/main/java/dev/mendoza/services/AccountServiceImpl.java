package dev.mendoza.services;

import java.util.ArrayList;
import java.util.List;

import dev.mendoza.daos.AccountDAO;
import dev.mendoza.models.Account;
import dev.mendoza.models.Transaction;
import dev.mendoza.models.User;

public class AccountServiceImpl implements AccountService {

	private static AccountDAO adao = new AccountDAO();

	@Override
	public boolean addAccount(Account a) {
		return adao.add(a);
	}

	@Override
	public Account getAccount(Integer accNum) {
		return adao.getAccount(accNum);
	}

	@Override
	public List<Account> getAllAccounts() {
		return adao.getAllAccounts();
	}
	
	@Override
	public List<Account> getAllAccountsByUser(User u) {
		List<Account> userAccounts = new ArrayList<Account>();
		for(Account a : adao.getAllAccounts()) {
			if(a.getUsername().equals(u.getUsername())) {
				userAccounts.add(a);
			}
		}
		if(userAccounts.isEmpty()) {
			System.out.println("No accounts under this user.");
			return null;
		}
		return userAccounts;
	}
	
	@Override
	public boolean approve(Account a) {
		return adao.approve(a);
	}

	@Override
	public boolean deny(Account a) {
		return adao.deny(a);
	}

	@Override
	public boolean changeBal(Account a, float newBal) {
		return adao.changeBal(a, newBal);
	}

	@Override
	public boolean withdraw(Account a, float with) {
		if(with <= 0 || a.getBalance() < with) {
			return false;
		}
		adao.changeBal(a, a.getBalance() - with);
		return true;
	}

	@Override
	public boolean deposit(Account a, float dep) {
		if(dep <= 0) {
			return false;
		}
		adao.changeBal(a, a.getBalance() + dep);
		return true;
	}

	// Account a giving Account b trans amount
	@Override
	public boolean transfer(Account a, Account b, float trans) {
		if(trans <= 0 || a.getBalance() < trans) {
			return false;
		}
		withdraw(a, trans);
		deposit(b, trans);
		return true;
	}

}
