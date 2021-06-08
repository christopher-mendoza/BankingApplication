package dev.mendoza.services;

import java.util.List;

import dev.mendoza.daos.AccountDAO;
import dev.mendoza.models.Account;

public class AccountServiceImpl implements AccountService {

	private static AccountDAO adao = new AccountDAO();

	@Override
	public Account addAccount(Account a) {
		return adao.add(a);
	}

	@Override
	public Account getAccount(Integer id) {
		return adao.getById(id);
	}

	@Override
	public List<Account> getAllAccounts() {
		return adao.getAll();
	}

	@Override
	public boolean updateAccount(Account a) {
		return adao.update(a);
	}

	@Override
	public boolean deleteAccount(Account a) {
		return adao.delete(a);
	}

}
