package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.AccountDAO;
import dev.mendoza.models.Account;
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
	public List<Account> getAllAccounts(User u) {
		return adao.getAllAccounts(u);
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

}
